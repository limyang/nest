package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.BaseTree;

import cascading.operation.Filter;
import cascading.operation.Function;
import cascading.pipe.CoGroup;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Merge;
import cascading.pipe.Pipe;
import cascading.pipe.assembly.Rename;
import cascading.pipe.assembly.Retain;
import cascading.pipe.joiner.LeftJoin;
import cascading.tuple.Fields;

import com.ebay.nest.NestException;
import com.ebay.nest.Pair;
import com.ebay.nest.TableSchema_remove;
import com.ebay.nest.cascading.operation.AggregateFunc;
import com.ebay.nest.cascading.operation.AnalyticalFunc;
import com.ebay.nest.cascading.operation.InsertOP;
import com.ebay.nest.cascading.operation.ScriptFilter;
import com.ebay.nest.cascading.operation.ScriptTupleFunction;
import com.ebay.nest.cascading.operation.UniqueOP;
import com.ebay.nest.cascading.operation.UpdateOp;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.utils.FieldsUtil;
import com.ebay.nest.utils.StringUtil;

public class QueryAnalyzer extends Analyzer {
	private ASTNode root;
	private QueryInfo queryInfo;

	protected final String SCRIPT_BEFORE = "\n{\n\treturn new cascading.tuple.Tuple( new Object[] {\n\t\t";
	protected final String SCRIPT_AFTER = "\n\t}); \n}";

	public QueryAnalyzer(StatementType type) throws NestException {
		super(type);
	}

	protected void reset() {
		super.reset();
		this.queryInfo = new QueryInfo(ctx, "S" + ctx.getCurrentID(), null, false);
		root = null;
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

	private void setColumnAliasesFromSelect(QueryInfo qb, ASTNode select) throws SemanticException {

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
					String tab = qb.getTabNameForAlias(alias);

					// try to fix this, it sucks
					Pipe tmp = ctx.getPipeForName(tab);
					Fields f = ctx.getFieldsForPipe(tmp);
					for (int m = 0; m < f.size(); m++) {
						String col = (String) f.get(m);
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
				setColumnAliasesFromSelect(qb, ast);
				break;
			case SQLParser.TOK_SELECT:
				qb.countSel();
				qb.setSelExprForClause(ctx_1.dest, ast);
				setColumnAliasesFromSelect(qb, ast);
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

	protected JoinInfo getJoinInfoFromCondn(QueryInfo qb, ASTNode condn, Fields leftFields, Fields rightFields)
			throws SemanticException {

		JoinInfo joinInfo = new JoinInfo();
		switch (condn.getToken().getType()) {
		case SQLParser.KW_OR:
			break;
		case SQLParser.KW_AND:
			ASTNode node1 = (ASTNode) condn.getChild(0);
			JoinInfo join1 = getJoinInfoFromCondn(qb, node1, leftFields, rightFields);

			ASTNode node2 = (ASTNode) condn.getChild(1);
			JoinInfo join2 = getJoinInfoFromCondn(qb, node2, leftFields, rightFields);
			joinInfo = JoinInfo.merge(join1, join2);
			break;
		case SQLParser.EQUAL:
			ASTNode firstCondNode = (ASTNode) condn.getChild(0);
			ASTNode secondCondNode = (ASTNode) condn.getChild(1);
			ExprInfo firstCondInfo = ExprUtil.processExpr(qb, firstCondNode, ctx);
			ExprInfo secondCondInfo = ExprUtil.processExpr(qb, secondCondNode, ctx);

			Fields firstFields = firstCondInfo.getFields();
			Fields secondFields = secondCondInfo.getFields();

			if (FieldsUtil.contain(leftFields, firstFields)) {
				if (FieldsUtil.contain(rightFields, secondFields)) {
					joinInfo.addJoinList(firstCondInfo, secondCondInfo);
				}

			} else if (FieldsUtil.contain(leftFields, secondFields)) {
				if (FieldsUtil.contain(rightFields, firstFields)) {
					joinInfo.addJoinList(secondCondInfo, firstCondInfo);
				}
			}
			break;

		default:

			break;
		}
		return joinInfo;

	}

	protected Pipe genPipeFromJoin(QueryInfo qb, Pipe pipe, List<Pair<ExprInfo, Fields>> joinList) {

		String script = SCRIPT_BEFORE;
		Fields outgoingFields = Fields.NONE;
		Fields fields = ctx.getFieldsForPipe(pipe);

		List<String> scriptList = new ArrayList<String>();
		for (int i = 0; i < fields.size(); i++) {
			scriptList.add((String) fields.get(i));
			outgoingFields = outgoingFields.append(new Fields(fields.get(i), fields.getType(i)));

		}

		for (int i = 0; i < joinList.size(); i++) {
			ExprInfo join = joinList.get(i).getLeft();
			scriptList.add(join.getScript());
			outgoingFields = outgoingFields.append(joinList.get(i).getRight());
		}

		script += StringUtil.join(scriptList.toArray(new String[scriptList.size()]), ",\n\t\t");
		script += SCRIPT_AFTER;

		// Function scriptFunction = new ScriptTupleFunction(outgoingFields,
		// script, fields.getTypesClasses());
		Function scriptFunction = new ScriptTupleFunction(outgoingFields, script);
		return pipe = new Each(pipe, scriptFunction, Fields.RESULTS);

	}

	private Pipe processWherePhase2(Pipe previous, ASTNode whereNode, QueryInfo qb) throws SemanticException {
		ASTNode exprNode = (ASTNode) whereNode;
		ExprInfo exprInfo = ExprUtil.processExpr(qb, exprNode, ctx);
		String script = "{\nreturn  ";

		script += "!" + exprInfo.getScript() + ";\n";
		script += "}";
		Fields incomingFields = ctx.getFieldsForPipe(previous);
		Filter expressionFilter = new ScriptFilter(script, incomingFields.getTypesClasses()); // handles
																								// coercions
		Pipe pipe = new Each(previous, expressionFilter);
		ctx.setPipeFields(pipe, ctx.getFieldsForPipe(previous));

		return pipe;
	}

	private void valideUpdateJoin(ASTNode join) throws SemanticException {
		switch (join.getToken().getType()) {
		case SQLParser.TOK_JOIN:
			ASTNode leftNode = (ASTNode) join.getChild(0);
			ASTNode rightNode = (ASTNode) join.getChild(1);
			if ((leftNode.getToken().getType() == SQLParser.TOK_TABREF)
					|| (leftNode.getToken().getType() == SQLParser.TOK_SUBQUERY)) {

			} else if (QueryUtil.isJoinToken(leftNode)) {
				valideUpdateJoin(leftNode);
			} else {
				assert (false);
			}

			if ((rightNode.getToken().getType() == SQLParser.TOK_TABREF)
					|| (rightNode.getToken().getType() == SQLParser.TOK_SUBQUERY)) {

			} else if (QueryUtil.isJoinToken(rightNode)) {
				valideUpdateJoin(rightNode);

			} else {
				assert (false);
			}
			break;
		case SQLParser.TOK_LEFTOUTERJOIN:
		case SQLParser.TOK_RIGHTOUTERJOIN:
		case SQLParser.TOK_FULLOUTERJOIN:
		default:
			throw new SemanticException(ErrorMsg.UPDATE_NOT_ALLOWED.getMsg(join));
		}

	}

	private Pipe processUpdatePhase2(QueryInfo qb, ASTNode ast) throws SemanticException {
		Fields updSetFields = Fields.NONE;
		Fields preSetFields = Fields.NONE;

		String updAlias = qb.getUpdateAlias();
		String updTab = qb.getTabNameForAlias(updAlias);
		if (updTab == null) {
			throw new SemanticException(ErrorMsg.DERIVED_NOT_ALLOWED_FOR_UPDATE.getMsg(ast));
		}
		List<String> aliases = qb.getAliases();
		Pipe updPipe = qb.getPipeForAlias(updAlias);
		Fields updFields = ctx.getFieldsForPipe(updPipe);

		if (qb.getJoinAST() != null) {
			ASTNode joinNode = qb.getJoinAST();
			valideUpdateJoin(joinNode);
		}
		Fields resFields = updFields;
		for (String alias : aliases) {
			if (alias.equals(updAlias)) {
				continue;
			}
			ASTNode joinCond = qb.getWhereExpr();
			if (joinCond == null) {
				continue;
			}
			Pipe prePipe = qb.getPipeForAlias(alias);
			Fields preFields = ctx.getFieldsForPipe(prePipe);

			JoinInfo joinInfo = getJoinInfoFromCondn(qb, joinCond, updFields, preFields);
			updPipe = genPipeFromJoin(qb, updPipe, joinInfo.getleftJoinList());
			prePipe = genPipeFromJoin(qb, prePipe, joinInfo.getRightJoinList());
			List<Pair<ExprInfo, Fields>> updList = joinInfo.getleftJoinList();
			List<Pair<ExprInfo, Fields>> preList = joinInfo.getRightJoinList();
			Fields updJoinFields = Fields.NONE;
			Fields preJoinFields = Fields.NONE;
			for (Pair<ExprInfo, Fields> upd : updList) {
				updJoinFields = updJoinFields.append(upd.getRight());
			}
			for (Pair<ExprInfo, Fields> pre : preList) {
				preJoinFields = preJoinFields.append(pre.getRight());
			}
			updPipe = new CoGroup(updPipe, updJoinFields, prePipe, preJoinFields, new LeftJoin());
			resFields = FieldsUtil.merge(resFields, preFields);
			ctx.setPipeFields(updPipe, resFields);
		}

		List<String> scriptList = new ArrayList<String>();
		int i = 0;
		Fields perAndSetFields = Fields.NONE;
		for (String setName : qb.getAllUpdToSetNode().keySet()) {
			ASTNode setNode = qb.getNodeForSetUpd(setName);
			ExprInfo setExpr = ExprUtil.processExpr(qb, setNode, ctx);
			Fields field = new Fields("$set_" + i, setExpr.getReturnType());
			preSetFields = preSetFields.append(field);

			setName = qb.getUpdateAlias() + QueryUtil.DOT_ALIAS + setName;

			updSetFields = updSetFields.append(new Fields(setName, updFields.getType(setName)));
			perAndSetFields = perAndSetFields.append(field);
			scriptList.add(setExpr.getScript());
			i++;
		}

		for (i = 0; i < resFields.size(); i++) {
			scriptList.add((String) resFields.get(i));
			perAndSetFields = perAndSetFields.append(new Fields(resFields.get(i), resFields.getType(i)));
		}

		String script = SCRIPT_BEFORE;
		script += StringUtil.join(scriptList.toArray(new String[scriptList.size()]), ",\n\t\t");
		script += SCRIPT_AFTER;

		Function scriptFunction = new ScriptTupleFunction(perAndSetFields, script);
		updPipe = new Each(updPipe, scriptFunction, Fields.RESULTS);

		ASTNode exprNode = (ASTNode) qb.getWhereExpr();
		script = "{\nreturn  ";
		if (exprNode == null) {
			script += "1==1;\n";
		} else {
			ExprInfo exprInfo = ExprUtil.processExpr(qb, exprNode, ctx);
			script += exprInfo.getScript() + ";\n";
		}
		script += "}";

		UpdateOp op = new UpdateOp(perAndSetFields, updSetFields, preSetFields, script, ctx.getTableSchema(updTab));
		updPipe = new Each(updPipe, op, Fields.RESULTS);

		updPipe = new Retain(updPipe, updFields);
		updPipe = new UniqueOP(updPipe, updFields);
		ctx.setPipeFields(updPipe, updFields);
		return updPipe;
	}

	private Pipe processDeletePhase2(QueryInfo qb, ASTNode ast) throws SemanticException {
		String delAlias = qb.getDeleteAlias();
		String delTab = qb.getTabNameForAlias(delAlias);
		if (delTab == null) {
			throw new SemanticException(ErrorMsg.DERIVED_NOT_ALLOWED_FOR_DELETE.getMsg(ast));
		}
		if (qb.getJoinAST() != null) {
			ASTNode joinNode = qb.getJoinAST();
			valideUpdateJoin(joinNode);
		}
		List<String> aliases = qb.getAliases();
		Pipe delPipe = qb.getPipeForAlias(delAlias);
		Fields delFields = ctx.getFieldsForPipe(delPipe);

		Fields resFields = delFields;
		for (String alias : aliases) {
			if (alias.equals(delAlias)) {
				continue;
			}
			ASTNode joinCond = qb.getWhereExpr();
			if (joinCond == null) {
				continue;
			}
			Pipe prePipe = qb.getPipeForAlias(alias);
			Fields preFields = ctx.getFieldsForPipe(prePipe);

			JoinInfo joinInfo = getJoinInfoFromCondn(qb, joinCond, delFields, preFields);
			delPipe = genPipeFromJoin(qb, delPipe, joinInfo.getleftJoinList());
			prePipe = genPipeFromJoin(qb, prePipe, joinInfo.getRightJoinList());
			List<Pair<ExprInfo, Fields>> updList = joinInfo.getleftJoinList();
			List<Pair<ExprInfo, Fields>> preList = joinInfo.getRightJoinList();
			Fields updJoinFields = Fields.NONE;
			Fields preJoinFields = Fields.NONE;
			for (Pair<ExprInfo, Fields> upd : updList) {
				updJoinFields = updJoinFields.append(upd.getRight());
			}
			for (Pair<ExprInfo, Fields> pre : preList) {
				preJoinFields = preJoinFields.append(pre.getRight());
			}
			delPipe = new CoGroup(delPipe, updJoinFields, prePipe, preJoinFields, new LeftJoin());
			resFields = FieldsUtil.merge(resFields, preFields);
			ctx.setPipeFields(delPipe, resFields);
		}
		delPipe = new Pipe("DEL PIPE " + qb.getId(), delPipe);

		ASTNode exprNode = (ASTNode) qb.getWhereExpr();
		String script = "{\nreturn  ";
		if (exprNode == null) {
			script += "1==1;\n";
		} else {
			ExprInfo exprInfo = ExprUtil.processExpr(qb, exprNode, ctx);
			script += exprInfo.getScript() + ";\n";
		}
		script += "}";

		Filter filter = new ScriptFilter(script);
		delPipe = new Each(delPipe, filter);
		delPipe = new Retain(delPipe, delFields);
		delPipe = new UniqueOP(delPipe, delFields);
		ctx.setPipeFields(delPipe, delFields);
		return delPipe;
	}

	private Pipe processSelectPhase2(Pipe pipe, ASTNode groupby, ASTNode having, ASTNode qualify, ASTNode select,
			QueryInfo qb) throws SemanticException {
		String selectScript = SCRIPT_BEFORE;
		Fields selectOutcomingFields = Fields.NONE;
		List<String> selScriptList = new ArrayList<String>();
		List<AggregateFuncInfo> aggregatorList = new ArrayList<AggregateFuncInfo>();
		Map<Fields, Set<AnalyticFuncInfo>> analyticalList = new HashMap<Fields, Set<AnalyticFuncInfo>>();

		Fields groupFields = Fields.NONE;
		if (groupby != null) {
			for (int i = 0; i < groupby.getChildCount(); i++) {
				ASTNode groupItem = (ASTNode) groupby.getChild(i);
				if (groupItem.getToken().getType() != SQLParser.DOT
						&& groupItem.getToken().getType() != SQLParser.TOK_TABLE_OR_COL) {
					throw new SemanticException();
				}
				ExprInfo itemExp = ExprUtil.processExpr(qb, groupItem, ctx);
				groupFields = groupFields.append(itemExp.getFields());
			}
		}

		for (int i = 0; i < select.getChildCount(); ++i) {
			ASTNode selectItem = (ASTNode) select.getChild(i);
			ASTNode selectExpr = (ASTNode) selectItem.getChild(0);
			String columnAlias = qb.getExprToColumnAlias(selectExpr);
			ExprInfo exprInfo = ExprUtil.processExpr(qb, selectExpr, ctx);
			for (Fields f : exprInfo.getAggregatorFields()) {
				AggregateFuncInfo info = exprInfo.getAggregatorForField(f);
				aggregatorList.add(info);
			}

			for (Fields f : exprInfo.getAnalyticalFields()) {
				AnalyticFuncInfo info = exprInfo.getAnalyticalForField(f);
				Fields partitionFields = info.getPartitionFields();
				Set<AnalyticFuncInfo> sets = analyticalList.get(partitionFields);
				if (sets == null) {
					sets = new HashSet<AnalyticFuncInfo>();
					analyticalList.put(partitionFields, sets);
				}
				sets.add(info);
			}

			selScriptList.add(exprInfo.getScript());
			selectOutcomingFields = selectOutcomingFields.append(new Fields(columnAlias, exprInfo.getReturnType()));
		}

		// process groupby and having
		if (!groupFields.equalsFields(Fields.NONE)) {
			Fields incomingFields = ctx.getFieldsForPipe(pipe);
			Fields outcomingFields = groupFields;
			Fields groupbyOutgoingFields = Fields.NONE;
			List<AggregateFuncType> typeList = new ArrayList<AggregateFuncType>();
			List<String> blockList = new ArrayList<String>();
			List<Class[]> innerList = new ArrayList<Class[]>();
			List<Class> returnList = new ArrayList<Class>();
			String havingScript = null;

			if (having != null) {
				ExprInfo havingInfo = ExprUtil.processExpr(qb, having, ctx);
				havingScript = havingInfo.getScript();
				for (Fields f : havingInfo.getAggregatorFields()) {
					AggregateFuncInfo info = havingInfo.getAggregatorForField(f);
					aggregatorList.add(info);
				}
			}

			for (AggregateFuncInfo info : aggregatorList) {
				typeList.add(info.getType());
				blockList.add(info.getBlock());
				returnList.add(info.getReturnClass());
				innerList.add(info.getInnerClasses());
				groupbyOutgoingFields = groupbyOutgoingFields.append(info.getDeclaredFields());
				outcomingFields = outcomingFields.append(info.getDeclaredFields());

			}

			Pipe groupbyPipe = new GroupBy(qb.getId() + ":" + "Group_By:" + "[" + FieldsUtil.toString(groupFields)
					+ "]", pipe, groupFields, null);
			AggregateFuncType[] types = typeList.toArray(new AggregateFuncType[typeList.size()]);
			String[] blocks = blockList.toArray(new String[blockList.size()]);
			Class[] returnClasses = returnList.toArray(new Class[returnList.size()]);
			Class[][] innerClasses = innerList.toArray(new Class[innerList.size()][]);

			AggregateFunc scriptAggregator = new AggregateFunc(groupbyOutgoingFields, types, blocks, returnClasses,
					innerClasses, incomingFields);
			pipe = new Every(groupbyPipe, scriptAggregator, outcomingFields);

			// having
			if (havingScript != null) {
				String script = "{\nreturn  ";
				script += "!" + havingScript + ";\n";
				script += "}";
				Filter expressionFilter = new ScriptFilter(script, outcomingFields.getTypesClasses()); // coercions
				pipe = new Each(pipe, expressionFilter);
			}

			ctx.setPipeFields(pipe, outcomingFields);

		}
		String qualifyScript = null;

		if (qualify != null) {
			ExprInfo qualifyInfo = ExprUtil.processExpr(qb, qualify, ctx);
			qualifyScript = qualifyInfo.getScript();
			for (Fields f : qualifyInfo.getAnalyticalFields()) {
				AnalyticFuncInfo info = qualifyInfo.getAnalyticalForField(f);
				Fields partitionFields = info.getPartitionFields();
				Set<AnalyticFuncInfo> sets = analyticalList.get(partitionFields);
				if (sets == null) {
					sets = new HashSet<AnalyticFuncInfo>();
					analyticalList.put(partitionFields, sets);
				}
				sets.add(info);
			}
		}

		for (Fields partitionFields : analyticalList.keySet()) {
			List<AnalyticFuncType> typeList = new ArrayList<AnalyticFuncType>();
			List<OrderType[]> orderTypesList = new ArrayList<OrderType[]>();
			List<Fields> orderList = new ArrayList<Fields>();
			List<String> blockList = new ArrayList<String>();
			List<Class[]> innerList = new ArrayList<Class[]>();
			List<Class> returnList = new ArrayList<Class>();
			List<Integer> upList = new ArrayList<Integer>();
			List<Integer> downList = new ArrayList<Integer>();
			Fields aggregatorOutgoingFields = Fields.NONE;
			Fields incomingFields = ctx.getFieldsForPipe(pipe);
			for (int i = 0; i < incomingFields.size(); i++) {
				Fields f = new Fields(incomingFields.get(i), incomingFields.getType(i));
				if (!FieldsUtil.contain(partitionFields, f)) {
					aggregatorOutgoingFields = aggregatorOutgoingFields.append(f);

				} else
					aggregatorOutgoingFields = aggregatorOutgoingFields.append(new Fields(incomingFields.get(i)
							+ "$new", incomingFields.getType(i)));

			}
			Fields outcomingFields = Fields.NONE;
			outcomingFields = outcomingFields.append(incomingFields);

			for (AnalyticFuncInfo info : analyticalList.get(partitionFields)) {
				typeList.add(info.getType());
				blockList.add(info.getBlock());
				returnList.add(info.getReturnClass());
				orderList.add(info.getOrderFields());
				orderTypesList.add(info.getOrderTypes());
				upList.add(info.getUprows());
				downList.add(info.getDownrows());
				innerList.add(info.getInnerClasses());
				aggregatorOutgoingFields = aggregatorOutgoingFields.append(info.getDeclaredFields());
				outcomingFields = outcomingFields.append(info.getDeclaredFields());
			}

			Pipe analyticalPipe = new GroupBy(qb.getId() + ":" + "Partition_By:" + "["
					+ FieldsUtil.toString(partitionFields) + "]", pipe, partitionFields);
			AnalyticFuncType[] types = typeList.toArray(new AnalyticFuncType[typeList.size()]);
			String[] blocks = blockList.toArray(new String[blockList.size()]);
			Class[] returnClasses = returnList.toArray(new Class[returnList.size()]);
			Class[][] innerClasses = innerList.toArray(new Class[innerList.size()][]);
			Integer[] _ups = upList.toArray(new Integer[upList.size()]);
			Integer[] _downs = downList.toArray(new Integer[downList.size()]);
			int ups[] = new int[_ups.length];
			int downs[] = new int[_downs.length];
			for (int i = 0; i < ups.length; i++) {
				ups[i] = _ups[i];
			}
			for (int i = 0; i < ups.length; i++) {
				downs[i] = _downs[i];
			}
			String[][] orders = new String[orderList.size()][];
			for (int i = 0; i < orderList.size(); i++) {
				Fields f = orderList.get(i);
				orders[i] = FieldsUtil.names(f);
			}
			OrderType[][] orderTypes = orderTypesList.toArray(new OrderType[0][]);

			AnalyticalFunc op = new AnalyticalFunc(aggregatorOutgoingFields, types, blocks, orders, orderTypes,
					returnClasses, innerClasses, ups, downs, incomingFields);
			pipe = new Every(analyticalPipe, op, outcomingFields);

			if (qualifyScript != null) {
				String script = "{\nreturn  ";
				script += "!" + qualifyScript + ";\n";
				script += "}";
				Filter filter = new ScriptFilter(script, outcomingFields.getTypesClasses()); // coercions
				pipe = new Each(pipe, filter);
			}

			ctx.setPipeFields(pipe, outcomingFields);
		}
		selectScript += StringUtil.join(selScriptList.toArray(new String[selScriptList.size()]), ",\n\t\t");
		selectScript += SCRIPT_AFTER;
		Fields incomingFields = ctx.getFieldsForPipe(pipe);
		String[] names = new String[incomingFields.size()];
		for (int i = 0; i < incomingFields.size(); i++) {
			names[i] = (String) incomingFields.get(i);
		}

		Function scriptFunction = new ScriptTupleFunction(selectOutcomingFields, selectScript, names,
				incomingFields.getTypesClasses());
		pipe = new Each(pipe, scriptFunction, Fields.RESULTS);
		ctx.setPipeFields(pipe, selectOutcomingFields);
		return pipe;

	}

	private Pipe processDistinct(Pipe pipe, QueryInfo qb) {
		Fields groupFields = ctx.getFieldsForPipe(pipe);

		pipe = new UniqueOP(qb.getId() + ":" + "Distinct", pipe, groupFields);
		ctx.setPipeFields(pipe, groupFields);

		return pipe;
	}

	private void processDestPhase2(Pipe pipe, String destName, QueryInfo qb) throws SemanticException {

		Set<String> tabAliases = qb.getTabAliases();
		Set<String> subAliases = qb.getSubqAliases();
		String insertTab = qb.getInsertTab();

		if (destName != null && !"".equals(destName) && !destName.startsWith("$")) {
			for (String alias : tabAliases) {
				String tab = qb.getTabNameForAlias(alias);
				Pipe tmp = ctx.getPipeForName(tab);
				ctx.addPipeSourceSet(pipe, ctx.getPipeSource(tmp));
			}
			if (insertTab != null) {
				Pipe tmp = ctx.getPipeForName(insertTab);
				ctx.addPipeSourceSet(pipe, ctx.getPipeSource(tmp));
			}
			for (String alias : subAliases) {
				Pipe tmp = qb.getPipeForAlias(alias);
				ctx.addPipeSourceSet(pipe, ctx.getPipeSource(tmp));
			}
			ctx.setPipe(destName, pipe);
		} else if ("$tmp".equalsIgnoreCase(destName)) {
			for (String alias : tabAliases) {
				String tab = qb.getTabNameForAlias(alias);
				Pipe tmp = ctx.getPipeForName(tab);
				ctx.addPipeSourceSet(pipe, ctx.getPipeSource(tmp));
			}
			for (String alias : subAliases) {
				Pipe tmp = qb.getPipeForAlias(alias);
				ctx.addPipeSourceSet(pipe, ctx.getPipeSource(tmp));
			}

		}

	}

	private Pipe doPhase2(ASTNode ast, QueryInfo qb) throws SemanticException {
		preProcess(qb);
		JoinUtil.preJoinFilter(qb, ctx);

		Pipe mp = null;
		QueryType qt = qb.getQueryType();
		Set<String> clauses = qb.getClauseNames();
		switch (qt) {
		case UPDATE:
			String updateAlias = qb.getUpdateAlias();
			Pipe up = qb.getPipeForAlias(updateAlias);
			mp = processUpdatePhase2(qb, ast);
			Fields upFields = ctx.getFieldsForPipe(up);
			Fields newFields = Fields.NONE;
			for (int i = 0; i < upFields.size(); i++) {

				String name = (String) upFields.get(i);
				Class type = (Class) upFields.getType(i);
				newFields = newFields.append(new Fields(name.substring(updateAlias.length()
						+ QueryUtil.DOT_ALIAS.length()), type));
			}
			mp = new Rename(mp, upFields, newFields);
			ctx.setPipeFields(mp, newFields);
			for (String clause : clauses) {
				String dest = qb.getDestForClause(clause);
				processDestPhase2(mp, dest, qb);
			}
			break;
		case DELETE:
			String delAlias = qb.getDeleteAlias();
			mp = processDeletePhase2(qb, ast);
			Fields delFields = ctx.getFieldsForPipe(mp);
			newFields = Fields.NONE;
			for (int i = 0; i < delFields.size(); i++) {

				String name = (String) delFields.get(i);
				Class type = (Class) delFields.getType(i);
				newFields = newFields.append(new Fields(
						name.substring(delAlias.length() + QueryUtil.DOT_ALIAS.length()), type));
			}
			mp = new Rename(mp, delFields, newFields);
			ctx.setPipeFields(mp, newFields);
			for (String clause : clauses) {
				String dest = qb.getDestForClause(clause);
				processDestPhase2(mp, dest, qb);
			}
			break;
		case INSERT:

			JoinTree jt = qb.getJoinTree();
			if (jt != null) {
				mp = JoinUtil.processJoinPhase2(qb, ctx, jt);
			} else {
				String defaultAlias = qb.getDefaultAlias();
				mp = qb.getPipeForAlias(defaultAlias);
			}

			if (qb.getWhereExpr() != null) {
				ASTNode whereNode = qb.getWhereExpr();
				mp = processWherePhase2(mp, whereNode, qb);
			}
			for (String clause : clauses) {
				ASTNode groupbyNode = qb.getGroupByForClause(clause);
				ASTNode selectNode = qb.getSelForClause(clause);
				ASTNode havingNode = qb.getHavingForClause(clause);
				ASTNode qualifyNode = qb.getQualifyExprForClause(clause);

				mp = processSelectPhase2(mp, groupbyNode, havingNode, qualifyNode, selectNode, qb);
				Fields insFields = Fields.NONE;
				Fields selFields = ctx.getFieldsForPipe(mp);
				List<String> cols = qb.getInsertCol();
				for (int i = 0; i < cols.size(); i++) {
					String colName = cols.get(i);
					insFields = insFields.append(new Fields(colName, selFields.getType(i)));
				}
				mp = new Rename(mp, selFields, insFields);
				String insTab = qb.getInsertTab();

				Pipe insPipe = ctx.getPipeForName(insTab);
				Fields tabFields = ctx.getFieldsForPipe(insPipe);
				TableSchema_remove schema = ctx.getTableSchema(insTab);
				InsertOP op = new InsertOP(tabFields, schema);
				mp = new Each(mp, op, Fields.RESULTS);
				insPipe = new Pipe("INSERT PIPE " + qb.getId(), insPipe);
				mp = new Merge(insPipe, mp);
				mp = new UniqueOP(mp, tabFields);
				ctx.setPipeFields(mp, tabFields);
				// qb.setPipeForClause(clause, mp);
				String dest = qb.getDestForClause(clause);
				processDestPhase2(mp, dest, qb);

			}
			break;
		case SELECT:
		default:
			jt = qb.getJoinTree();
			if (jt != null) {
				mp = JoinUtil.processJoinPhase2(qb, ctx, jt);
			} else {
				String defaultAlias = qb.getDefaultAlias();
				mp = qb.getPipeForAlias(defaultAlias);
			}

			if (qb.getWhereExpr() != null) {
				ASTNode whereNode = qb.getWhereExpr();
				mp = processWherePhase2(mp, whereNode, qb);
			}

			for (String clause : clauses) {
				ASTNode groupbyNode = qb.getGroupByForClause(clause);
				ASTNode selectNode = qb.getSelForClause(clause);
				ASTNode havingNode = qb.getHavingForClause(clause);
				ASTNode qualifyNode = qb.getQualifyExprForClause(clause);

				mp = processSelectPhase2(mp, groupbyNode, havingNode, qualifyNode, selectNode, qb);
				if (qb.isDistinct()) {
					mp = processDistinct(mp, qb);
				}

				// qb.setPipeForClause(clause, mp);
				String dest = qb.getDestForClause(clause);
				processDestPhase2(mp, dest, qb);
			}
		}

		return mp;
	}

	private void renameAllTable(QueryInfo qb) throws SemanticException {
		Set<String> tabAliases = qb.getTabAliases();
		for (String alias : tabAliases) {
			String tab = qb.getTabNameForAlias(alias);
			Pipe pipe = ctx.getPipeForName(tab);
			Fields colFields = ctx.getFieldsForPipe(pipe);
			if (colFields == null) {
				throw new SemanticException(ErrorMsg.MISSING_DEFINE_FOR_TABLE.getMsg(tab));
			}

			Fields aliasFields = Fields.NONE;
			for (int i = 0; i < colFields.size(); i++) {
				String col = (String) colFields.get(i);
				qb.setAliasCols(alias, col);
				aliasFields = aliasFields.append(new Fields(alias + QueryUtil.DOT_ALIAS + col, colFields.getType(i)));
			}
			pipe = new Rename(pipe, colFields, aliasFields);
			ctx.setPipeFields(pipe, aliasFields);
			qb.setAliasPipe(alias, pipe);
		}
	}

	private void renameAllSubQuery(QueryInfo qb) throws SemanticException {
		Set<String> subqAliases = qb.getSubqAliases();
		for (String alias : subqAliases) {
			QueryExpr subqExpr = qb.getSubqForAlias(alias);
			ASTNode subqNode = subqExpr.getNode();
			QueryInfo subqb = subqExpr.getQB();
			Pipe pipe = doPhase2(subqNode, subqb);
			Set<String> sources = ctx.getPipeSource(pipe);
			Fields subqFields = ctx.getFieldsForPipe(pipe);
			Fields aliasFields = Fields.NONE;
			for (int i = 0; i < subqFields.size(); i++) {
				String name = (String) subqFields.get(i);
				qb.setAliasCols(alias, name);
				aliasFields = aliasFields.append(new Fields(alias + QueryUtil.DOT_ALIAS + name, subqFields.getType(i)));
			}
			pipe = new Rename(pipe, subqFields, aliasFields);
			ctx.setPipeFields(pipe, aliasFields);
			ctx.addPipeSourceSet(pipe, sources);
			qb.setAliasPipe(alias, pipe);
		}

	}

	private void preProcess(QueryInfo qb) throws SemanticException {
		renameAllTable(qb);
		renameAllSubQuery(qb);

	}

	public void analyzeInternal(ASTNode ast) throws SemanticException {
		this.root = ast;
		processPositionAlias(ast);

		Phase1Ctx ctx_1 = initPhase1Ctx();
		if (!doPhase1(this.root, queryInfo, ctx_1)) {
			return;
		}

		doPhase2(this.root, queryInfo);

		return;
	}
}
