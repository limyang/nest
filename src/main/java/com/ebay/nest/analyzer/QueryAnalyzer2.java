package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.BaseTree;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;

import com.ebay.nest.Column;
import com.ebay.nest.NestException;
import com.ebay.nest.Table;
import com.ebay.nest.Table_remove;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.plan.FilterDesc;
import com.ebay.nest.plan.Operator;
import com.ebay.nest.plan.OperatorDesc;
import com.ebay.nest.plan.OperatorFactory;

public class QueryAnalyzer2 extends Analyzer {
	private ASTNode root;
	private QueryInfo queryInfo;
	private LinkedHashMap<Operator<? extends OperatorDesc>, OpParseContext> opParseCtx;

	public QueryAnalyzer2(StatementType type) throws NestException {
		super(type);
		opParseCtx = new LinkedHashMap<Operator<? extends OperatorDesc>, OpParseContext>();
	}

	private static class Phase1Ctx {
		String dest;
		int nextNum;
	}

	public Phase1Ctx initPhase1Ctx() {
		Phase1Ctx ctx_1 = new Phase1Ctx();
		ctx_1.nextNum = 0;
		ctx_1.dest = "reduce";
		return ctx_1;
	}

	private void processPositionAlias(ASTNode ast) throws SemanticException {

		if (ast.getChildCount() == 0) {
			return;
		}

		ASTNode selectNode = null;
		ASTNode groupbyNode = null;
		ASTNode orderbyNode = null;
		boolean isAllCol;

		int child_count = ast.getChildCount();
		for (int child_pos = 0; child_pos < child_count; ++child_pos) {
			ASTNode node = (ASTNode) ast.getChild(child_pos);
			int type = node.getToken().getType();
			if (type == SQLParser.TOK_SELECT) {
				selectNode = node;
			} else if (type == SQLParser.TOK_GROUPBY) {
				groupbyNode = node;
			} else if (type == SQLParser.TOK_ORDERBY) {
				orderbyNode = node;
			}
		}

		if (selectNode != null) {
			int selectExpCnt = selectNode.getChildCount();

			if (groupbyNode != null) {
				for (int child_pos = 0; child_pos < groupbyNode.getChildCount(); ++child_pos) {
					ASTNode node = (ASTNode) groupbyNode.getChild(child_pos);
					if (node.getToken().getType() == SQLParser.Number) {
						int pos = Integer.parseInt(node.getText());
						if (pos > 0 && pos <= selectExpCnt) {
							groupbyNode.setChild(child_pos, (BaseTree) selectNode.getChild(pos - 1).getChild(0));
						} else {
							throw new SemanticException(
									ErrorMsg.INVALID_POSITION_ALIAS_IN_GROUPBY.getMsg("Position alias: " + pos
											+ " does not exist\n" + "The Select List is indexed from 1 to "
											+ selectExpCnt));
						}
					}
				}
			}
			if (orderbyNode != null) {
				isAllCol = false;
				for (int child_pos = 0; child_pos < selectNode.getChildCount(); ++child_pos) {
					ASTNode node = (ASTNode) selectNode.getChild(child_pos).getChild(0);
					if (node.getToken().getType() == SQLParser.TOK_ALLCOLREF) {
						isAllCol = true;
					}
				}
				for (int child_pos = 0; child_pos < orderbyNode.getChildCount(); ++child_pos) {
					ASTNode colNode = (ASTNode) orderbyNode.getChild(child_pos);
					ASTNode node = (ASTNode) colNode.getChild(0);
					if (node.getToken().getType() == SQLParser.Number) {
						if (!isAllCol) {
							int pos = Integer.parseInt(node.getText());
							if (pos > 0 && pos <= selectExpCnt) {
								colNode.setChild(0, (BaseTree) selectNode.getChild(pos - 1).getChild(0));
							} else {
								throw new SemanticException(
										ErrorMsg.INVALID_POSITION_ALIAS_IN_ORDERBY.getMsg("Position alias: " + pos
												+ " does not exist\n" + "The Select List is indexed from 1 to "
												+ selectExpCnt));
							}
						} else {
							throw new SemanticException(ErrorMsg.NO_SUPPORTED_ORDERBY_ALLCOLREF_POS.getMsg());
						}
					}
				}
			}
		}

		for (int child_pos = 0; child_pos < child_count; ++child_pos) {
			processPositionAlias((ASTNode) ast.getChild(child_pos));
		}
		return;
	}

	private static ASTNode genSelectExpr(String alias, String col) {
		ASTNode t = new ASTNode(new CommonToken(SQLParser.Identifier, alias));
		ASTNode tp = new ASTNode(new CommonToken(SQLParser.TOK_TABLE_OR_COL, "TOK_TABLE_OR_COL"));
		tp.addChild(t);
		ASTNode c = new ASTNode(new CommonToken(SQLParser.Identifier, col));
		ASTNode p = new ASTNode(new CommonToken(SQLParser.DOT, "."));
		p.addChild(tp);
		p.addChild(c);
		return p;
	}

	private void setColumnAliasesFromSelect(ASTNode select, QueryInfo qb) throws SemanticException {

		for (int i = 0; i < select.getChildCount(); ++i) {
			ASTNode selectItem = (ASTNode) select.getChild(i);
			if (((selectItem.getChildCount() == 2) || (selectItem.getChildCount() == 3 && selectItem.getChild(2)
					.getType() == SQLParser.TOK_WINDOWSPEC))) {
				String columnAlias = selectItem.getChild(1).getText();
				if (qb.exitsColumn(columnAlias)) {
					throw new SemanticException(ErrorMsg.DUPLICAT_COLUMN_NAME.getMsg(selectItem));
				}
				qb.addCol(columnAlias);
				qb.setExprToColumnAlias((ASTNode) selectItem.getChild(0), columnAlias);
				continue;
			} else if (selectItem.getChildCount() == 1) {
				ASTNode expr = (ASTNode) selectItem.getChild(0);
				ASTNode table_or_col = (ASTNode) selectItem.getChild(0).getChild(0);
				String columnAlias = null;
				if (expr.getToken().getType() == SQLParser.DOT) {
					if (table_or_col.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
						columnAlias = expr.getChild(1).getText();
					} else if (table_or_col.getToken().getType() == SQLParser.DOT) {
						table_or_col = (ASTNode) table_or_col.getChild(0);
						if (table_or_col.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
							columnAlias = expr.getChild(1).getText();
						}
					}
				} else if (expr.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
					columnAlias = table_or_col.getText();
				} else if (expr.getToken().getType() == SQLParser.TOK_ALLCOLREF) {
					ASTNode tabNode = (ASTNode) expr.getChild(0);
					String alias = QueryUtil.getTabNameFromNode(tabNode);
					Table table = qb.getMetaData().getTableForAlias(alias);
					List<Column> f = table.getAllColumns();
					for (int m = 0; m < f.size(); m++) {
						String col = f.get(m).getName();
						if (qb.exitsColumn(col)) {
							throw new SemanticException(ErrorMsg.DUPLICAT_COLUMN_NAME.getMsg(selectItem));
						}
						qb.addCol(col);
						expr = genSelectExpr(alias, col);
						qb.setExprToColumnAlias(expr, col);
						selectItem = new ASTNode(new CommonToken(SQLParser.TOK_SELEXPR, "TOK_SELEXPR"));
						selectItem.addChild(expr);
						if (m == 0) {
							select.setChild(i, selectItem);
						} else {
							select.insertChild(++i, selectItem);
						}
					}

					continue;
				}
				if (expr.getToken().getType() == SQLParser.TOK_FUNCTION) {
					String expr_flattened = expr.toStringTree();
					String expr_no_tok = expr_flattened.replaceAll("TOK_\\S+", "");
					String expr_formatted = expr_no_tok.replaceAll("\\W", " ").trim().replaceAll("\\s+", "_");
					columnAlias = expr_formatted.concat("_" + i);
				}
				if (columnAlias == null) {
					columnAlias = "col_" + i;
				}

				if (qb.exitsColumn(columnAlias)) {
					throw new SemanticException(ErrorMsg.DUPLICAT_COLUMN_NAME.getMsg(selectItem));
				}
				qb.addCol(columnAlias);
				qb.setExprToColumnAlias(expr, columnAlias);
				continue;

			}

			throw new SemanticException(ErrorMsg.NO_COLUMN_FOR_ALIAS.getMsg(selectItem));

		}

	}

	public static String generateErrorMessage(ASTNode ast, String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(ast.getLine());
		sb.append(":");
		sb.append(ast.getCharPositionInLine());
		sb.append(" ");
		sb.append(message);
		sb.append(". Error encountered near token '");
		sb.append(ErrorMsg.getText(ast));
		sb.append("'");
		return sb.toString();
	}

	private String processTable(QueryInfo qb, ASTNode tabref) throws SemanticException {

		int aliasIndex = 0;
		for (int index = 1; index < tabref.getChildCount(); index++) {
			aliasIndex = index;
		}

		ASTNode tabNameNode = (ASTNode) (tabref.getChild(0));

		String tabName = QueryUtil.getTabNameFromNode(tabNameNode);

		String alias;
		if (aliasIndex != 0) {
			alias = tabref.getChild(aliasIndex).getText();
		} else {
			alias = QueryUtil.getAliasFromNode(tabNameNode);
		}

		if (qb.exists(alias)) {
			throw new SemanticException(ErrorMsg.AMBIGUOUS_TABLE_ALIAS.getMsg(tabref.getChild(aliasIndex)));
		}

		qb.setTabAlias(alias, tabName);
		qb.addAlias(alias);

		return alias;
	}

	public void doPhase1QBExpr(ASTNode ast, QueryExpr qbexpr, String id, String alias) throws SemanticException {

		assert (ast.getToken() != null);
		switch (ast.getToken().getType()) {
		case SQLParser.TOK_SUBQUERY_STATEMENT: {
			processPositionAlias(ast);
			QueryInfo qb = new QueryInfo(ctx, id, alias, true);
			Phase1Ctx ctx_1 = initPhase1Ctx();
			doPhase1(ast, qb, ctx_1);
			qbexpr.setOpcode(QueryExpr.Opcode.NULLOP);
			qbexpr.setQB(qb);
			qbexpr.setNode(ast);
		}
			break;
		}
	}

	private String processSubQuery(QueryInfo qb, ASTNode subq) throws SemanticException {

		if (subq.getChildCount() != 2) {
			throw new SemanticException(ErrorMsg.NO_SUBQUERY_ALIAS.getMsg(subq));
		}
		ASTNode subqref = (ASTNode) subq.getChild(0);
		String alias = subq.getChild(1).getText();

		QueryExpr qbexpr = new QueryExpr(alias);

		doPhase1QBExpr(subqref, qbexpr, qb.getId(), alias);

		if (qb.exists(alias)) {
			throw new SemanticException(ErrorMsg.AMBIGUOUS_TABLE_ALIAS.getMsg(subq.getChild(1)));
		}

		qb.setSubqAlias(alias, qbexpr);
		qb.addAlias(alias);

		return alias;
	}

	private void processJoin(QueryInfo qb, ASTNode join) throws SemanticException {
		int numChildren = join.getChildCount();
		if ((numChildren != 2) && (numChildren != 3) && join.getToken().getType() != SQLParser.TOK_UNIQUEJOIN) {
			throw new SemanticException(generateErrorMessage(join, "Join with multiple children"));
		}

		for (int num = 0; num < numChildren; num++) {
			ASTNode child = (ASTNode) join.getChild(num);
			if (child.getToken().getType() == SQLParser.TOK_TABREF) {
				qb.setDefaultAlias(processTable(qb, child));
			} else if (child.getToken().getType() == SQLParser.TOK_SUBQUERY) {
				qb.setDefaultAlias(processSubQuery(qb, child));
			} else if (QueryUtil.isJoinToken(child)) {
				processJoin(qb, child);
			}
		}
	}

	private String processAliasNode(QueryInfo qb, ASTNode aliasNode) throws SemanticException {
		if (aliasNode.getToken().getType() != SQLParser.TOK_ALIAS) {
			return null;
		}
		int child_account = aliasNode.getChildCount();
		String alias = aliasNode.getChild(0).getText();
		if (child_account == 1) {
			if (!qb.exists(alias)) {
				throw new SemanticException(ErrorMsg.ALIAS_NOT_FROM.getMsg(aliasNode));
			}
			return alias;
		}
		if (qb.exists(alias)) {
			throw new SemanticException(ErrorMsg.AMBIGUOUS_TABLE_ALIAS.getMsg(aliasNode.getChild(0)));
		}
		ASTNode tabNameNode = (ASTNode) (aliasNode.getChild(1));
		String tabName = QueryUtil.getTabNameFromNode(tabNameNode);

		qb.setTabAlias(alias, tabName);
		qb.addAlias(alias);

		return alias;
	}

	public boolean doPhase1(ASTNode ast, QueryInfo qb, Phase1Ctx ctx_1) throws SemanticException {

		boolean phase1Result = true;
		boolean skipRecursion = false;

		if (ast.getToken() != null) {
			skipRecursion = true;
			switch (ast.getToken().getType()) {
			case SQLParser.TOK_SELECTDI:
				qb.markDistinct();
				qb.countSel();
				qb.setSelExprForClause(ctx_1.dest, ast);
				setColumnAliasesFromSelect(ast, qb);
				break;
			case SQLParser.TOK_SELECT:
				qb.countSel();
				qb.setSelExprForClause(ctx_1.dest, ast);
				setColumnAliasesFromSelect(ast, qb);
				break;
			case SQLParser.TOK_WHERE:
				qb.setWhrExprForClause((ASTNode) ast.getChild(0));
				break;
			case SQLParser.TOK_DESTINATION:
				ctx_1.dest = "dest-" + ctx_1.nextNum;
				ctx_1.nextNum++;
				ASTNode destNode = (ASTNode) ast.getChild(0);
				if (qb.getIsSubQ()) {
					if ((destNode.getToken().getType() != SQLParser.TOK_DIR)
							|| (((ASTNode) destNode.getChild(0)).getToken().getType() != SQLParser.TOK_TMP_FILE)) {
						throw new SemanticException(ErrorMsg.NO_INSERT_INSUBQUERY.getMsg(ast));
					}
				}
				String destName;
				switch (destNode.getToken().getType()) {
				case SQLParser.TOK_TABNAME:
					destName = QueryUtil.getTabNameFromNode(destNode);
					break;
				case SQLParser.TOK_DIR:
					if (((ASTNode) destNode.getChild(0)).getToken().getType() == SQLParser.TOK_TMP_FILE) {
						destName = "$tmp";
						break;
					}
					destName = "$";
					break;
				case SQLParser.TOK_VIEWNAME:
					destName = destNode.getChild(0).getText();
					break;
				default:
					destName = "";
					break;
				}

				qb.setDestForClause(ctx_1.dest, destName);
				break;

			case SQLParser.TOK_FROM:
				int child_count = ast.getChildCount();
				if (child_count != 1) {
					throw new SemanticException(generateErrorMessage(ast, "Multiple Children " + child_count));
				}
				ASTNode frm = (ASTNode) ast.getChild(0);
				if (frm.getToken().getType() == SQLParser.TOK_TABREF) {
					qb.setDefaultAlias(processTable(qb, frm));
				} else if (frm.getToken().getType() == SQLParser.TOK_SUBQUERY) {
					qb.setDefaultAlias(processSubQuery(qb, frm));
				} else if (QueryUtil.isJoinToken(frm)) {
					processJoin(qb, frm);
					qb.setJoinAST(frm);
				}
				break;
			case SQLParser.TOK_GROUPBY:

				if (qb.getSelForClause(ctx_1.dest).getToken().getType() == SQLParser.TOK_SELECTDI) {
					throw new SemanticException(generateErrorMessage(ast,
							ErrorMsg.SELECT_DISTINCT_WITH_GROUPBY.getMsg()));
				}
				qb.setGroupByExprForClause(ctx_1.dest, ast);
				skipRecursion = true;
				break;

			case SQLParser.TOK_HAVING:
				qb.setHavingExprForClause(ctx_1.dest, (ASTNode) ast.getChild(0));
				break;

			case SQLParser.TOK_INSERT:
				child_count = ast.getChildCount();
				ASTNode insNode = (ASTNode) ast.getChild(0);
				String insTab = QueryUtil.getTabNameFromNode(insNode);
				qb.setInsertTab(insTab);
				if (ctx_1.dest == null) {
					String dest = insTab;
					ctx_1.dest = "dest-" + ctx_1.nextNum;
					ctx_1.nextNum++;
					qb.setDestForClause(ctx_1.dest, dest);
				}

				for (int i = 1; i < child_count; i++) {
					String col = ast.getChild(i).getText();
					qb.addInsertCol(col);

				}
				skipRecursion = false;
				break;
			case SQLParser.TOK_UPDATE:
				child_count = ast.getChildCount();
				ASTNode updNode = (ASTNode) ast.getChild(0);
				String updAlias = processAliasNode(qb, updNode);
				qb.setUpdateAlias(updAlias);
				if (ctx_1.dest == null) {
					String dest = qb.getTabNameForAlias(updAlias);
					ctx_1.dest = "dest-" + ctx_1.nextNum;
					ctx_1.nextNum++;
					qb.setDestForClause(ctx_1.dest, dest);
				}

				for (int i = 1; i < child_count; i++) {
					ASTNode setItem = (ASTNode) ast.getChild(i);
					String column = setItem.getChild(0).getText();
					ASTNode itemExpr = (ASTNode) setItem.getChild(1);
					qb.setUpdSetNode(column, itemExpr);
				}
				break;
			case SQLParser.TOK_DELETE:
				child_count = ast.getChildCount();
				String delAlias;
				if (child_count > 0) {
					ASTNode deleteNode = (ASTNode) ast.getChild(0);
					delAlias = processAliasNode(qb, deleteNode);
				} else {
					delAlias = qb.getDefaultAlias();
				}
				qb.setDeleteAlias(delAlias);
				if (ctx_1.dest == null) {
					String dest = qb.getTabNameForAlias(delAlias);
					ctx_1.dest = "dest-" + ctx_1.nextNum;
					ctx_1.nextNum++;
					qb.setDestForClause(ctx_1.dest, dest);
				}

				break;
			case SQLParser.TOK_QUALIFY:
				qb.setQualifyExprForClause(ctx_1.dest, (ASTNode) ast.getChild(0));
				break;
			case SQLParser.TOK_UPDATE_QUERY:
				qb.setQueryType(QueryType.UPDATE);
				skipRecursion = false;
				break;
			case SQLParser.TOK_SELECT_QUERY:
				qb.setQueryType(QueryType.SELECT);
				skipRecursion = false;
				break;
			case SQLParser.TOK_INSERT_QUERY:
				qb.setQueryType(QueryType.INSERT);
				skipRecursion = false;
				break;
			case SQLParser.TOK_DELETE_QUERY:
				qb.setQueryType(QueryType.DELETE);
				skipRecursion = false;
			default:
				skipRecursion = false;
				break;
			}
		}

		if (!skipRecursion) {
			int child_count = ast.getChildCount();
			for (int child_pos = 0; child_pos < child_count && phase1Result; ++child_pos) {
				phase1Result = phase1Result && doPhase1((ASTNode) ast.getChild(child_pos), qb, ctx_1);
			}
		}
		return phase1Result;
	}

	public <T extends OperatorDesc> Operator<T> putOpInsertMap(Operator<T> op, RowResolver rr) {
		OpParseContext ctx = new OpParseContext(rr);
		opParseCtx.put(op, ctx);
		op.augmentPlan();
		return op;
	}

	private Operator genFilterPlan(QueryInfo qb, ASTNode condn, Operator input) throws SemanticException {

		OpParseContext inputCtx = opParseCtx.get(input);
		RowResolver inputRR = inputCtx.getRowResolver();
		Operator output = putOpInsertMap(OperatorFactory.getAndMakeChild(new FilterDesc(
				genExprNodeDesc(condn, inputRR), false), new RowSchema(inputRR.getColumnInfos()), input), inputRR);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Created Filter Plan for " + qb.getId() + " row schema: " + inputRR.toString());
		}
		return output;
	}

	private void pushJoinFilters(QueryInfo qb, JoinTree joinTree, Map<String, Operator> map) throws SemanticException {
		if (joinTree.getJoinSrc() != null) {
			pushJoinFilters(qb, joinTree.getJoinSrc(), map);
		}
		ArrayList<ArrayList<ASTNode>> filters = joinTree.getFiltersForPushing();
		int pos = 0;
		for (String src : joinTree.getBaseSrc()) {
			if (src != null) {
				Operator srcOp = map.get(src);
				ArrayList<ASTNode> filter = filters.get(pos);
				for (ASTNode cond : filter) {
					srcOp = genFilterPlan(qb, cond, srcOp);
				}
				map.put(src, srcOp);
			}
			pos++;
		}
	}

	private JoinType getType(JoinCond[] conds) {
		JoinType type = conds[0].getJoinType();
		for (int k = 1; k < conds.length; k++) {
			if (type != conds[k].getJoinType()) {
				return null;
			}
		}
		return type;
	}

	private void mergeJoinTree(QueryInfo qb) {
		JoinTree tree = qb.getJoinTree();
		if (tree.getJoinSrc() == null) {
			return;
		}
		// make array with QBJoinTree : outer most(0) --> inner most(n)
		List<JoinTree> trees = new ArrayList<JoinTree>();
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
				JoinType currType = getType(node.getJoinCondNode());
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

	public void analyzeInternal(ASTNode ast) throws NestException {
		processPositionAlias(ast);
		Phase1Ctx ctx_1 = initPhase1Ctx();

		if (!doPhase1(ast, queryInfo, ctx_1)) {
			return;
		}

		Operator sinkOp = genPlan(queryInfo);

		return;
	}

}
