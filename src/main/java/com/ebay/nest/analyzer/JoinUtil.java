package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cascading.operation.Debug;
import cascading.pipe.CoGroup;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Retain;
import cascading.pipe.joiner.InnerJoin;
import cascading.pipe.joiner.Joiner;
import cascading.pipe.joiner.LeftJoin;
import cascading.pipe.joiner.OuterJoin;
import cascading.pipe.joiner.RightJoin;
import cascading.tuple.Fields;
import cascading.util.NullNotEquivalentComparator;

import com.ebay.nest.NestContext;
import com.ebay.nest.cascading.operation.ExprAppend;
import com.ebay.nest.cascading.operation.ExprFilter;
import com.ebay.nest.cascading.operation.PostJoinOperation;
import com.ebay.nest.expr.AliasColFinder;
import com.ebay.nest.expr.AndExpr;
import com.ebay.nest.expr.EqualExpr;
import com.ebay.nest.expr.ExprDepthLastWalker;
import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.expr.ExprVisitor;
import com.ebay.nest.expr.GreatEqualExpr;
import com.ebay.nest.expr.GreatExpr;
import com.ebay.nest.expr.LessEqualExpr;
import com.ebay.nest.expr.LessExpr;
import com.ebay.nest.expr.NotEqualExpr;
import com.ebay.nest.expr.NotExpr;
import com.ebay.nest.expr.OrExpr;
import com.ebay.nest.expr.UDFExpr;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.utils.FieldsUtil;
import com.ebay.nest.utils.StringUtil;

public class JoinUtil {

	private static int next_join_id = 0;

	public static int next_join_id() {
		return next_join_id++;
	}

	public static JoinTree genJoinTree(QueryInfo qb, ASTNode joinParseTree) throws SemanticException {
		JoinTree joinTree = new JoinTree();
		JoinType joinType;

		switch (joinParseTree.getToken().getType()) {
		case SQLParser.TOK_LEFTOUTERJOIN:
			joinType = JoinType.LEFTOUTER;
			break;
		case SQLParser.TOK_RIGHTOUTERJOIN:
			joinType = JoinType.RIGHTOUTER;
			break;
		case SQLParser.TOK_FULLOUTERJOIN:
			joinType = JoinType.FULLOUTER;
			break;
		default:
			joinType = JoinType.INNER;
			break;
		}

		joinTree.setJoinType(joinType);

		ASTNode left = (ASTNode) joinParseTree.getChild(0);
		ASTNode right = (ASTNode) joinParseTree.getChild(1);

		if ((left.getToken().getType() == SQLParser.TOK_TABREF)
				|| (left.getToken().getType() == SQLParser.TOK_SUBQUERY)) {
			String tableName = QueryUtil.getAliasFromNode((ASTNode) left.getChild(0)).toLowerCase();
			String alias = left.getChildCount() == 1 ? tableName : StringUtil.unescapeIdentifier(left
					.getChild(left.getChildCount() - 1).getText().toLowerCase());
			String[] leftAliases = new String[1];
			leftAliases[0] = alias;
			joinTree.setLeftAliases(leftAliases);
			String[] children = new String[2];
			children[0] = alias;
			joinTree.setBaseSrc(children);
			joinTree.setId(qb.getId());
		} else if (QueryUtil.isJoinToken(left)) {
			JoinTree leftTree = genJoinTree(qb, left);
			joinTree.setJoinSrc(leftTree);
			joinTree.setLeftJoin(leftTree);
			String[] leftChildLeftAliases = leftTree.getLeftAliases();
			String[] leftChildRightAliases = leftTree.getRightAliases();
			String leftAliases[] = new String[leftChildLeftAliases.length + leftChildRightAliases.length];
			for (int i = 0; i < leftChildLeftAliases.length; i++) {
				leftAliases[i] = leftChildLeftAliases[i];
			}
			for (int i = 0; i < leftChildRightAliases.length; i++) {
				leftAliases[leftChildLeftAliases.length + i] = leftChildRightAliases[i];
			}
			joinTree.setLeftAliases(leftAliases);
		} else {
			assert (false);
		}

		if ((right.getToken().getType() == SQLParser.TOK_TABREF)
				|| (right.getToken().getType() == SQLParser.TOK_SUBQUERY)) {
			String tableName = QueryUtil.getAliasFromNode((ASTNode) right.getChild(0)).toLowerCase();
			String alias = right.getChildCount() == 1 ? tableName : StringUtil.unescapeIdentifier(right
					.getChild(right.getChildCount() - 1).getText().toLowerCase());

			String[] rightAliases = new String[1];
			rightAliases[0] = alias;
			joinTree.setRightAliases(rightAliases);
			String[] children = joinTree.getBaseSrc();
			if (children == null) {
				children = new String[2];
			}
			children[1] = alias;
			joinTree.setBaseSrc(children);

			joinTree.setId(qb.getId());
		} else if (QueryUtil.isJoinToken(right)) {
			JoinTree rightTree = genJoinTree(qb, right);
			joinTree.setRightJoin(rightTree);
			String[] rightChildLeftAliases = rightTree.getLeftAliases();
			String[] rightChildRightAliases = rightTree.getRightAliases();
			String rightAliases[] = new String[rightChildLeftAliases.length + rightChildRightAliases.length];
			for (int i = 0; i < rightChildLeftAliases.length; i++) {
				rightAliases[i] = rightChildLeftAliases[i];
			}
			for (int i = 0; i < rightChildRightAliases.length; i++) {
				rightAliases[rightChildLeftAliases.length + i] = rightChildRightAliases[i];
			}

			joinTree.setRightAliases(rightAliases);
		} else {
			assert false;
		}

		ASTNode joinCond = (ASTNode) joinParseTree.getChild(2);
		if (joinCond == null && joinType != JoinType.INNER) {
			throw new SemanticException(ErrorMsg.MISSING_JOIN_CONDN.getMsg(joinParseTree));
		} else if (joinCond == null) {
			joinCond = qb.getWhereExpr();
		}
		joinTree.setJoinCondNode(joinCond);
		ExprDesc joinExpr = ExprUtil2.processExpr(qb, joinCond);
		joinTree.setJoinExpr(joinExpr);

		(new JoinVisitor(joinExpr, joinTree)).visit();

		return joinTree;
	}

	private static Map<String, Set<String>> mergeAliasCols(Map<String, Set<String>> allcols,
			Map<String, Set<String>> aliascol) {
		for (String alias : aliascol.keySet()) {
			Set<String> cols = allcols.get(alias);
			if (cols == null) {
				cols = new HashSet<String>();
				allcols.put(alias, cols);
			}
			cols.addAll(aliascol.get(alias));
		}
		return allcols;

	}

	public static void preJoinFilter(QueryInfo qb, NestContext ctx) throws SemanticException {
		QueryType qt = qb.getQueryType();
		Set<String> clauses = qb.getClauseNames();
		Map<String, Set<String>> allcols = new HashMap();
		switch (qt) {
		case UPDATE:
			for (String setName : qb.getAllUpdToSetNode().keySet()) {
				ASTNode setNode = qb.getNodeForSetUpd(setName);
				ExprDesc eo = ExprUtil2.processExpr(qb, setNode);
				allcols = mergeAliasCols(allcols, (new AliasColFinder(eo)).getAliasMap());
			}
			if (qb.getWhereExpr() != null) {
				ASTNode whereNode = qb.getWhereExpr();
				ExprDesc joinExpr = ExprUtil2.processExpr(qb, whereNode);
				allcols = mergeAliasCols(allcols, (new AliasColFinder(joinExpr)).getAliasMap());
			}
			break;
		case DELETE:
			if (qb.getWhereExpr() != null) {
				ASTNode whereNode = qb.getWhereExpr();
				ExprDesc joinExpr = ExprUtil2.processExpr(qb, whereNode);
				allcols = mergeAliasCols(allcols, (new AliasColFinder(joinExpr)).getAliasMap());
			}
			break;
		case INSERT:
		case SELECT:
			ASTNode joinNode = qb.getJoinAST();
			if (joinNode != null) {
				JoinTree jt = JoinUtil.genJoinTree(qb, joinNode);
				qb.setJoinTree(jt);
				List<ExprDesc> eos = jt.getAllcondExpr();
				for (ExprDesc eo : eos) {
					allcols = mergeAliasCols(allcols, (new AliasColFinder(eo)).getAliasMap());
				}
			}

			if (qb.getWhereExpr() != null) {
				ASTNode whereNode = qb.getWhereExpr();
				ExprDesc joinExpr = ExprUtil2.processExpr(qb, whereNode);
				allcols = mergeAliasCols(allcols, (new AliasColFinder(joinExpr)).getAliasMap());
			}

			for (String clause : clauses) {
				ASTNode groupbyNode = qb.getGroupByForClause(clause);
				ASTNode selectNode = qb.getSelForClause(clause);
				ASTNode havingNode = qb.getHavingForClause(clause);
				ASTNode qualifyNode = qb.getQualifyExprForClause(clause);
				if (havingNode != null) {
					ExprDesc havingExpr = ExprUtil2.processExpr(qb, havingNode);
					allcols = mergeAliasCols(allcols, (new AliasColFinder(havingExpr)).getAliasMap());
				}
				if (qualifyNode != null) {
					ExprDesc qualifyExpr = ExprUtil2.processExpr(qb, qualifyNode);
					allcols = mergeAliasCols(allcols, (new AliasColFinder(qualifyExpr)).getAliasMap());
				}
				if (groupbyNode != null) {
					for (int i = 0; i < groupbyNode.getChildCount(); i++) {
						ASTNode groupItem = (ASTNode) groupbyNode.getChild(i);
						ExprDesc groupExpr = ExprUtil2.processExpr(qb, groupItem);
						allcols = mergeAliasCols(allcols, (new AliasColFinder(groupExpr)).getAliasMap());
					}
				}
				if (selectNode != null) {
					for (int i = 0; i < selectNode.getChildCount(); ++i) {
						ASTNode item = (ASTNode) selectNode.getChild(i);
						ASTNode selItem = (ASTNode) item.getChild(0);
						ExprDesc selExpr = ExprUtil2.processExpr(qb, selItem);
						allcols = mergeAliasCols(allcols, (new AliasColFinder(selExpr)).getAliasMap());

					}
				}
			}

			break;
		default:
			break;

		}

	}

	public static Pipe processJoinPhase2(QueryInfo qb, NestContext ctx, JoinTree joinTree) throws SemanticException {
		int next_key = next_join_id();
		JoinType joinType = joinTree.getJoinType();
		Joiner joiner;
		switch (joinType) {
		case INNER:
			joiner = new InnerJoin();
			break;
		case LEFTOUTER:
			joiner = new LeftJoin();
			break;
		case RIGHTOUTER:
			joiner = new RightJoin();
			break;
		case FULLOUTER:
			joiner = new OuterJoin();
			break;
		default:
			throw new SemanticException(ErrorMsg.INVLID_JOIN_TYPE.getMsg(joinTree.getJoinCondNode()));

		}
		Pipe leftPipe = null;
		Pipe rightPipe = null;

		if (joinTree.getLeftJoin() != null) {
			leftPipe = processJoinPhase2(qb, ctx, joinTree.getLeftJoin());
		} else {
			String leftAlias = joinTree.getLeftAliases()[0];
			leftPipe = qb.getPipeForAlias(leftAlias);
		}
		final Fields leftOrigFields = ctx.getFieldsForPipe(leftPipe);

		List<ExprDesc> leftFilters = new ArrayList<ExprDesc>();
		if (joinTree.getLeftFilter().size() != 0) {
			leftFilters.addAll(joinTree.getLeftFilter());
		}
		if (joinType == JoinType.INNER || joinType == JoinType.LEFTOUTER) {
			ASTNode ast = qb.getWhereExpr();
			if (ast != null) {
				ExprDesc expr = ExprUtil2.processExpr(qb, ast);
				(new WhereFilterVisiter(expr, joinTree.getLeftAliases(), leftFilters)).visit();
			}
		}
		ExprFilter ef = new ExprFilter(leftFilters);
		leftPipe = new Each(leftPipe, ef);

		List<ExprDesc> lks = joinTree.getLeftJoinKey();
		Fields leftGroupFields = Fields.NONE;
		Fields leftGenFields = leftOrigFields;
		for (int i = 0; i < lks.size(); i++) {
			leftGroupFields = leftGroupFields.append(new Fields("$LEFT_JOIN_KEY_" + i));
			leftGenFields = leftGenFields.append(new Fields("$LEFT_JOIN_KEY_" + i));
		}

		ExprAppend ea = new ExprAppend(lks, leftGenFields);
		leftPipe = new Each(leftPipe, ea);

		if (joinTree.getRightJoin() != null) {
			rightPipe = processJoinPhase2(qb, ctx, joinTree.getRightJoin());
		} else {
			String rightAlias = joinTree.getRightAliases()[0];
			rightPipe = qb.getPipeForAlias(rightAlias);
		}
		final Fields rightOrigFields = ctx.getFieldsForPipe(rightPipe);

		List<ExprDesc> rightFilters = new ArrayList<ExprDesc>();
		if (joinTree.getRightFilter().size() != 0) {
			rightFilters.addAll(joinTree.getRightFilter());
		}

		if (joinType == JoinType.INNER || joinType == JoinType.RIGHTOUTER) {
			ASTNode ast = qb.getWhereExpr();
			if (ast != null) {
				ExprDesc expr = ExprUtil2.processExpr(qb, ast);
				(new WhereFilterVisiter(expr, joinTree.getRightAliases(), rightFilters)).visit();
			}
		}

		ef = new ExprFilter(rightFilters);
		rightPipe = new Each(rightPipe, ef);

		List<ExprDesc> rks = joinTree.getRightJoinKey();
		Fields rightGroupFields = Fields.NONE;
		Fields rightGenFields = rightOrigFields;
		for (int i = 0; i < rks.size(); i++) {
			rightGroupFields = rightGroupFields.append(new Fields("$RIGHT_JOIN_KEY_" + i));
			rightGenFields = rightGenFields.append(new Fields("$RIGHT_JOIN_KEY_" + i));

		}

		ea = new ExprAppend(rks, rightGenFields);
		rightPipe = new Each(rightPipe, ea);

		int ljs = leftGroupFields.size();
		int rjs = rightGroupFields.size();
		if (ljs == 0 || rjs == 0) {
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_CROSS_JOIN.getMsg(joinTree.getJoinCondNode()));
		}
		if (ljs != rjs) {
			// never let it happen !!!!
			assert (false);
		}

		Comparator[] camparators = new Comparator[ljs];
		for (int i = 0; i < ljs; i++) {
			camparators[i] = new NullNotEquivalentComparator();
		}
		leftGroupFields.setComparators(camparators);
		rightGroupFields.setComparators(camparators);
		Pipe pipe = new CoGroup(qb.getId() + ":" + "Join_" + next_key, leftPipe, leftGroupFields, rightPipe,
				rightGroupFields, joiner);
		Fields declaredFields = FieldsUtil.merge(leftOrigFields, rightOrigFields);

		PostJoinOperation jos;
		switch (joinType) {
		case LEFTOUTER:
			jos = new PostJoinOperation(joinTree.getPostJoinCond(), leftOrigFields, rightOrigFields, declaredFields);
			pipe = new Every(pipe, jos, declaredFields);
			break;
		case RIGHTOUTER:

			jos = new PostJoinOperation(joinTree.getPostJoinCond(), rightOrigFields, leftOrigFields, declaredFields);
			pipe = new Every(pipe, jos, declaredFields);
			break;
		case INNER:

			ExprFilter innerPost = new ExprFilter(joinTree.getPostJoinCond());

			pipe = new Each(pipe, innerPost);
			break;
		case FULLOUTER:
			break;
		default:
			break;
		}
		pipe = new Retain(pipe, declaredFields);

		ctx.setPipeFields(pipe, declaredFields);

		return pipe;

	}

	public static class WhereFilterVisiter extends ExprVisitor {

		private static final long serialVersionUID = 1L;
		private String[] aliases;
		List<ExprDesc> lists;

		protected WhereFilterVisiter(ExprDesc op, String[] aliases, List<ExprDesc> lists) {
			super(op, new ExprDepthLastWalker(op));
			this.aliases = aliases;
			this.lists = lists;
		}

		@Override
		public boolean visitAnd(AndExpr andExpr) {
			ExprDesc leftExpr = andExpr.getLhs();
			ExprDesc rightExpr = andExpr.getRhs();

			AliasColFinder finder = new AliasColFinder(leftExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(leftExpr);
			} else {
				leftExpr.visit(this);
			}

			finder = new AliasColFinder(rightExpr);
			Map<String, Set<String>> rightAliasMap = finder.getAliasMap();

			if (isBelong(aliases, rightAliasMap.keySet())) {
				lists.add(rightExpr);
			} else {
				rightExpr.visit(this);
			}

			return true;

		}

		public boolean visitNot(NotExpr notExpr) {
			AliasColFinder finder = new AliasColFinder(notExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(notExpr);
			}
			return true;
		}

		public boolean visitUDF(UDFExpr udfExpr) {
			AliasColFinder finder = new AliasColFinder(udfExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(udfExpr);
			}
			return true;
		}

		public boolean visitNotEqual(NotEqualExpr notEqualExpr) {
			AliasColFinder finder = new AliasColFinder(notEqualExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(notEqualExpr);
			}
			return true;
		}

		public boolean visitLess(LessExpr lessExpr) {
			AliasColFinder finder = new AliasColFinder(lessExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(lessExpr);
			}
			return true;
		}

		public boolean visitLessEqual(LessEqualExpr lessEqualExpr) {
			AliasColFinder finder = new AliasColFinder(lessEqualExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(lessEqualExpr);
			}
			return true;
		}

		public boolean visitGreatEqualThan(GreatEqualExpr greatEqualExpr) {
			AliasColFinder finder = new AliasColFinder(greatEqualExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(greatEqualExpr);
			}
			return true;
		}

		public boolean visitGreat(GreatExpr greatExpr) {
			AliasColFinder finder = new AliasColFinder(greatExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(greatExpr);
			}
			return true;
		}

		public boolean visitOr(OrExpr orExpr) {
			AliasColFinder finder = new AliasColFinder(orExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();
			if (isBelong(aliases, leftAliasMap.keySet())) {
				lists.add(orExpr);
			}
			return true;
		}
	}

	private static boolean isBelong(String[] aliases, Set<String> condAliases) {
		boolean isbelong = true;

		for (String alias : condAliases) {
			boolean find = false;
			for (int i = 0; i < aliases.length; i++) {
				String a = aliases[i];
				if (alias.equalsIgnoreCase(a)) {
					find = true;
					break;
				}
			}
			if (!find) {
				isbelong = false;
				break;
			}

		}
		return isbelong;

	}

	private static class JoinVisitor extends ExprVisitor {

		private static final long serialVersionUID = 1L;
		private JoinTree joinTree;

		protected JoinVisitor(ExprDesc op, JoinTree joinTree) {
			super(op, new ExprDepthLastWalker(op));
			this.joinTree = joinTree;
		}

		@Override
		public boolean visitAnd(AndExpr andExpr) {
			andExpr.getLhs().visit(this);
			andExpr.getRhs().visit(this);
			return true;

		}

		@Override
		public boolean visitEqual(EqualExpr equalExpr) {
			JoinType type = joinTree.getJoinType();
			String[] leftAliases = joinTree.getLeftAliases();
			String[] rightAliases = joinTree.getRightAliases();

			ExprDesc leftExpr = equalExpr.getLhs();
			ExprDesc rightExpr = equalExpr.getRhs();

			AliasColFinder finder = new AliasColFinder(leftExpr);
			Map<String, Set<String>> leftAliasMap = finder.getAliasMap();

			finder = new AliasColFinder(rightExpr);
			Map<String, Set<String>> rightAliasMap = finder.getAliasMap();

			if (isBelong(leftAliases, leftAliasMap.keySet())) {
				if (isBelong(leftAliases, rightAliasMap.keySet())) {
					switch (type) {
					case LEFTOUTER:
						joinTree.addPostJoinCond(equalExpr);
						break;
					case INNER:
					case FULLOUTER:
					case RIGHTOUTER:
						joinTree.addLeftFilter(equalExpr);
					default:
						joinTree.addPostJoinCond(equalExpr);
						break;
					}

				} else if (isBelong(rightAliases, rightAliasMap.keySet())) {
					joinTree.addLeftkey(leftExpr);
					joinTree.addRightkey(rightExpr);
				}

			} else if (isBelong(rightAliases, leftAliasMap.keySet())) {
				if (isBelong(rightAliases, rightAliasMap.keySet())) {
					switch (type) {
					case RIGHTOUTER:
						joinTree.addPostJoinCond(equalExpr);
						break;
					case INNER:
					case FULLOUTER:
					case LEFTOUTER:
						joinTree.addRightFilter(equalExpr);
						break;
					default:
						joinTree.addPostJoinCond(equalExpr);
						break;
					}
				} else if (isBelong(leftAliases, rightAliasMap.keySet())) {
					joinTree.addLeftkey(rightExpr);
					joinTree.addRightkey(leftExpr);
				}
			} else {
				joinTree.addPostJoinCond(equalExpr);
			}
			return true;
		}

		public boolean visitNot(NotExpr notExpr) {
			joinTree.addPostJoinCond(notExpr);
			return true;
		}

		public boolean visitUDF(UDFExpr udfExpr) {
			joinTree.addPostJoinCond(udfExpr);
			return true;
		}

		public boolean visitNotEqual(NotEqualExpr notEqualExpr) {
			joinTree.addPostJoinCond(notEqualExpr);
			return true;
		}

		public boolean visitLess(LessExpr lessExpr) {
			joinTree.addPostJoinCond(lessExpr);
			return true;
		}

		public boolean visitLessEqual(LessEqualExpr lessEqualExpr) {
			joinTree.addPostJoinCond(lessEqualExpr);
			return true;
		}

		public boolean visitGreatEqualThan(GreatEqualExpr greatEqualExpr) {
			joinTree.addPostJoinCond(greatEqualExpr);
			return true;
		}

		public boolean visitGreat(GreatExpr greatExpr) {
			joinTree.addPostJoinCond(greatExpr);
			return true;
		}

		public boolean visitOr(OrExpr orExpr) {
			joinTree.addPostJoinCond(orExpr);
			return true;
		}

	}

}
