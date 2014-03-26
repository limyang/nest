package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.plan.Operator;
import com.ebay.nest.plan.OperatorDesc;

public class QueryAnalyzerPhase2 {

	private Operator genPlan(QueryExpr qbexpr) throws SemanticException {
		if (qbexpr.getOpcode() == QueryExpr.Opcode.NULLOP) {
			return genPlan(qbexpr.getQB());
		}
		if (qbexpr.getOpcode() == QueryExpr.Opcode.UNION) {
			Operator qbexpr1Ops = genPlan(qbexpr.getQBExpr1());
			Operator qbexpr2Ops = genPlan(qbexpr.getQBExpr2());

			return genUnionPlan(qbexpr.getAlias(), qbexpr.getQBExpr1().getAlias(), qbexpr1Ops, qbexpr.getQBExpr2()
					.getAlias(), qbexpr2Ops);
		}
		return null;
	}

	private void mergeJoinTree(QueryInfo qb) {
		JoinTree tree = qb.getJoinTree();
		if (tree.getJoinSrc() == null) {
			return;
		}
		// make array with QBJoinTree : outer most(0) --> inner most(n)
		List<QBJoinTree> trees = new ArrayList<QBJoinTree>();
		for (; tree != null; tree = tree.getJoinSrc()) {
			trees.add(tree);
		}
		// merging from 'target'(inner) to 'node'(outer)
		for (int i = trees.size() - 1; i >= 0; i--) {
			JoinTree target = trees.get(i);
			if (target == null) {
				continue;
			}
			JoinType prevType = null; // save join type
			for (int j = i - 1; j >= 0; j--) {
				JoinTree node = trees.get(j);
				if (node == null) {
					continue;
				}
				JoinType currType = getType(node.getJoinCond());
				if (prevType != null && prevType != currType) {
					break;
				}
				int pos = findMergePos(node, target);
				if (pos >= 0) {
					// for outer joins, it should not exceed 16 aliases (short type)
					if (!node.getNoOuterJoin() || !target.getNoOuterJoin()) {
						if (node.getRightAliases().length + target.getRightAliases().length + 1 > 16) {
							LOG.info(ErrorMsg.JOINNODE_OUTERJOIN_MORETHAN_16);
							continue;
						}
					}
					mergeJoins(qb, node, target, pos);
					trees.set(j, null);
					continue; // continue merging with next alias
				}
				if (prevType == null) {
					prevType = currType;
				}
			}
		}
		// reconstruct join tree
		JoinTree current = null;
		for (int i = 0; i < trees.size(); i++) {
			JoinTree target = trees.get(i);
			if (target == null) {
				continue;
			}
			if (current == null) {
				qb.setJoinTree(current = target);
			} else {
				current.setJoinSrc(target);
				current = target;
			}
		}
	}

	public Operator genPlan(QueryInfo qb) throws SemanticException {

		Map<String, Operator> aliasToOpInfo = new HashMap<String, Operator>();

		for (String alias : qb.getSubqAliases()) {
			QueryExpr qbexpr = qb.getSubqForAlias(alias);
			aliasToOpInfo.put(alias, genPlan(qbexpr));
			qbexpr.setAlias(alias);
		}

		for (String alias : qb.getTabAliases()) {
			Operator op = genTablePlan(alias, qb);
			aliasToOpInfo.put(alias, op);
		}

		Operator srcOpInfo = null;
		Operator lastPTFOp = null;

		if (qb.getJoinAST() != null) {
			ASTNode joinExpr = qb.getJoinAST();

			if (joinExpr.getToken().getType() == SQLParser.TOK_UNIQUEJOIN) {
				QBJoinTree joinTree = genUniqueJoinTree(qb, joinExpr, aliasToOpInfo);
				qb.setQbJoinTree(joinTree);
			} else {
				QBJoinTree joinTree = JoinUtil.genJoinTree(qb, joinExpr, aliasToOpInfo);
				qb.setQbJoinTree(joinTree);
				mergeJoinTree(qb);
			}

			pushJoinFilters(qb, qb.getJoinAST(), aliasToOpInfo);
			srcOpInfo = genJoinPlan(qb, aliasToOpInfo);
		} else {
			srcOpInfo = aliasToOpInfo.values().iterator().next();
			srcOpInfo = lastPTFOp != null ? lastPTFOp : srcOpInfo;
		}

		Operator bodyOpInfo = genBodyPlan(qb, srcOpInfo);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Created Plan for Query Block " + qb.getId());
		}

		this.qb = qb;
		return bodyOpInfo;

	}

	private Map<String, Operator<? extends OperatorDesc>> createInputForDests(QueryInfo qb,
			Operator<? extends OperatorDesc> input, Set<String> dests) throws SemanticException {
		Map<String, Operator<? extends OperatorDesc>> inputs = new HashMap<String, Operator<? extends OperatorDesc>>();
		for (String dest : dests) {
			inputs.put(dest, input);
		}
		return inputs;
	}

	private Operator genBodyPlan(QueryInfo qb, Operator input) throws SemanticException {

		TreeSet<String> ks = new TreeSet<String>(qb.getClauseNames());
		Map<String, Operator<? extends OperatorDesc>> inputs = createInputForDests(qb, input, ks);

		List<ASTNode> commonDistinctExprs = getCommonDistinctExprs(qb, input);

		// Consider a query like:
		//
		// from src
		// insert overwrite table dest1 select col1, count(distinct colx) group by col1
		// insert overwrite table dest2 select col2, count(distinct colx) group by col2;
		//
		// With HIVE_OPTIMIZE_MULTI_GROUPBY_COMMON_DISTINCTS set to true, first we spray by the distinct
		// value (colx), and then perform the 2 groups bys. This makes sense if map-side aggregation is
		// turned off. However, with maps-side aggregation, it might be useful in some cases to treat
		// the 2 inserts independently, thereby performing the query above in 2MR jobs instead of 3
		// (due to spraying by distinct key first).
		boolean optimizeMultiGroupBy = commonDistinctExprs != null
				&& conf.getBoolVar(HiveConf.ConfVars.HIVE_OPTIMIZE_MULTI_GROUPBY_COMMON_DISTINCTS);

		Operator curr = input;

		// if there is a single distinct, optimize that. Spray initially by the
		// distinct key,
		// no computation at the mapper. Have multiple group by operators at the
		// reducer - and then
		// proceed
		if (optimizeMultiGroupBy) {
			curr = createCommonReduceSink(qb, input);

			RowResolver currRR = opParseCtx.get(curr).getRowResolver();
			// create a forward operator
			input = putOpInsertMap(
					OperatorFactory.getAndMakeChild(new ForwardDesc(), new RowSchema(currRR.getColumnInfos()), curr),
					currRR);

			for (String dest : ks) {
				curr = input;
				curr = genGroupByPlan2MRMultiGroupBy(dest, qb, curr);
				curr = genSelectPlan(dest, qb, curr);
				Integer limit = qbp.getDestLimit(dest);
				if (limit != null) {
					curr = genLimitMapRedPlan(dest, qb, curr, limit.intValue(), true);
					qb.getParseInfo().setOuterQueryLimit(limit.intValue());
				}
				curr = genFileSinkPlan(dest, qb, curr);
			}
		} else {
			List<List<String>> commonGroupByDestGroups = null;

			// If we can put multiple group bys in a single reducer, determine suitable groups of
			// expressions, otherwise treat all the expressions as a single group
			if (conf.getBoolVar(HiveConf.ConfVars.HIVEMULTIGROUPBYSINGLEREDUCER)) {
				try {
					commonGroupByDestGroups = getCommonGroupByDestGroups(qb, inputs);
				} catch (SemanticException e) {
					LOG.error("Failed to group clauses by common spray keys.", e);
				}
			}

			if (commonGroupByDestGroups == null) {
				commonGroupByDestGroups = new ArrayList<List<String>>();
				commonGroupByDestGroups.add(new ArrayList<String>(ks));
			}

			if (!commonGroupByDestGroups.isEmpty()) {

				// Iterate over each group of subqueries with the same group by/distinct keys
				for (List<String> commonGroupByDestGroup : commonGroupByDestGroups) {
					if (commonGroupByDestGroup.isEmpty()) {
						continue;
					}

					String firstDest = commonGroupByDestGroup.get(0);
					input = inputs.get(firstDest);

					// Constructs a standard group by plan if:
					// There is no other subquery with the same group by/distinct keys or
					// (There are no aggregations in a representative query for the group and
					// There is no group by in that representative query) or
					// The data is skewed or
					// The conf variable used to control combining group bys into a single reducer is false
					if (commonGroupByDestGroup.size() == 1
							|| (qbp.getAggregationExprsForClause(firstDest).size() == 0 && getGroupByForClause(qbp,
									firstDest).size() == 0) || conf.getBoolVar(HiveConf.ConfVars.HIVEGROUPBYSKEW)
							|| !conf.getBoolVar(HiveConf.ConfVars.HIVEMULTIGROUPBYSINGLEREDUCER)) {

						// Go over all the destination tables
						for (String dest : commonGroupByDestGroup) {
							curr = inputs.get(dest);

							if (qbp.getWhrForClause(dest) != null) {
								curr = genFilterPlan(dest, qb, curr);
							}

							if (qbp.getAggregationExprsForClause(dest).size() != 0
									|| getGroupByForClause(qbp, dest).size() > 0) {
								// multiple distincts is not supported with skew in data
								if (conf.getBoolVar(HiveConf.ConfVars.HIVEGROUPBYSKEW)
										&& qbp.getDistinctFuncExprsForClause(dest).size() > 1) {
									throw new SemanticException(ErrorMsg.UNSUPPORTED_MULTIPLE_DISTINCTS.getMsg());
								}
								// insert a select operator here used by the ColumnPruner to reduce
								// the data to shuffle
								curr = insertSelectAllPlanForGroupBy(curr);
								if (conf.getBoolVar(HiveConf.ConfVars.HIVEMAPSIDEAGGREGATE)) {
									if (!conf.getBoolVar(HiveConf.ConfVars.HIVEGROUPBYSKEW)) {
										curr = genGroupByPlanMapAggrNoSkew(dest, qb, curr);
									} else {
										curr = genGroupByPlanMapAggr2MR(dest, qb, curr);
									}
								} else if (conf.getBoolVar(HiveConf.ConfVars.HIVEGROUPBYSKEW)) {
									curr = genGroupByPlan2MR(dest, qb, curr);
								} else {
									curr = genGroupByPlan1MR(dest, qb, curr);
								}
							}

							curr = genPostGroupByBodyPlan(curr, dest, qb);
						}
					} else {
						curr = genGroupByPlan1ReduceMultiGBY(commonGroupByDestGroup, qb, input);
					}
				}
			}
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Created Body Plan for Query Block " + qb.getId());
		}

		return curr;
	}

}
