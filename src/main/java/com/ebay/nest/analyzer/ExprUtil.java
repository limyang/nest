package com.ebay.nest.analyzer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cascading.tuple.Fields;

import com.ebay.nest.Data;
import com.ebay.nest.NestContext;
import com.ebay.nest.UDF;
import com.ebay.nest.arithmetic.BaseUtil;
import com.ebay.nest.arithmetic.BooleanUtil;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.arithmetic.CompareUtil;
import com.ebay.nest.arithmetic.MathUtil;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;
import com.ebay.nest.utils.FieldsUtil;
import com.ebay.nest.utils.StringUtil;

public class ExprUtil {
	private static final String DOT_ALIAS = "$";

	public static final String BASEUTIL_CLASS = BaseUtil.class.getName();
	public static final String BOOLEANUTIL_CLASS = BooleanUtil.class.getName();
	public static final String MATHUTIL_CLASS = MathUtil.class.getName();
	public static final String CASTUTIL_CLASS = CastUtil.class.getName();
	public static final String COMPAREUTIL_CLASS = CompareUtil.class.getName();

	public static final String EXPR_PACKAGE = "com.ebay.nest.udf";

	public static final Set<String> AGGREGATOR_FUNC = new HashSet<String>();
	public static final Set<String> ANALYTICAL_FUNC = new HashSet<String>();
	private static final String SCRIPT_BEFORE = "\n{\n\treturn new cascading.tuple.Tuple( new Object[] {\n\t\t";
	private static final String SCRIPT_AFTER = "\n\t}); \n}";

	private static int next = 0;

	static {

		for (AggregateFuncType value : AggregateFuncType.values()) {

			AGGREGATOR_FUNC.add(value.toString());
		}

		for (AnalyticFuncType value : AnalyticFuncType.values()) {

			ANALYTICAL_FUNC.add(value.toString());
		}

	}

	private static int next() {
		return next++;
	}

	private static String[] processDotNode(ASTNode dotNode) {

		if (dotNode.getToken().getType() != SQLParser.DOT) {
			return null;
		}
		String tab = null, col = null, db = null;
		ASTNode tableNode = (ASTNode) dotNode.getChild(0);

		if (tableNode.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
			tab = StringUtil.unescapeIdentifier(tableNode.getChild(0).getText().toLowerCase());
			col = StringUtil.unescapeIdentifier(dotNode.getChild(1).getText().toLowerCase());
		} else if (tableNode.getToken().getType() == SQLParser.DOT) {
			ASTNode dbNode = (ASTNode) tableNode.getChild(0);
			if (dbNode.getToken().getType() == SQLParser.TOK_TABLE_OR_COL) {
				db = StringUtil.unescapeIdentifier(dbNode.getChild(0).getText().toLowerCase());
				tab = StringUtil.unescapeIdentifier(tableNode.getChild(1).getText().toLowerCase());
				col = StringUtil.unescapeIdentifier(dotNode.getChild(1).getText().toLowerCase());
			}
		}

		return new String[] { tab, col, db };

	}

	private static WindowInfo processWindowSpec(ASTNode ast, QueryInfo qb, NestContext ctx) throws SemanticException {
		if (ast.getToken().getType() != SQLParser.TOK_WINDOWSPEC) {
			return null;
		}
		Fields partitionFields = Fields.NONE;
		Fields orderFields = Fields.NONE;
		List<OrderType> ordreTypes = new ArrayList<OrderType>();
		int up = -1;
		int down = -1;
		for (int i = 0; i < ast.getChildCount(); i++) {
			ASTNode child = (ASTNode) ast.getChild(i);
			switch (child.getToken().getType()) {
			case SQLParser.TOK_PARTITIONINGSPEC:
				for (int j = 0; j < child.getChildCount(); j++) {
					ASTNode past = (ASTNode) child.getChild(j);
					switch (past.getToken().getType()) {
					case SQLParser.TOK_DISTRIBUTEBY:
						for (int k = 0; k < past.getChildCount(); k++) {
							ASTNode expr = (ASTNode) past.getChild(k);
							if (expr.getToken().getType() != SQLParser.DOT
									&& expr.getToken().getType() != SQLParser.TOK_TABLE_OR_COL) {
								throw new SemanticException(ErrorMsg.INVALID_PARTIIONBY_CLAUSE.getMsg(expr));
							}
							ExprInfo exprInfo = ExprUtil.processExpr(qb, expr, ctx);
							partitionFields = partitionFields.append(exprInfo.getFields());
						}
						break;
					case SQLParser.TOK_ORDERBY:
						for (int k = 0; k < past.getChildCount(); k++) {
							ASTNode order = (ASTNode) past.getChild(k);
							ASTNode expr = (ASTNode) order.getChild(0);

							if (expr.getToken().getType() != SQLParser.DOT
									&& expr.getToken().getType() != SQLParser.TOK_TABLE_OR_COL) {
								throw new SemanticException(ErrorMsg.INVALID_PARTIIONBY_CLAUSE.getMsg(expr));
							}
							if (order.getToken().getType() == SQLParser.TOK_TABSORTCOLNAMEASC) {
								ordreTypes.add(OrderType.ASC);
							} else {
								ordreTypes.add(OrderType.DESC);
							}
							ExprInfo exprInfo = ExprUtil.processExpr(qb, expr, ctx);
							orderFields = orderFields.append(exprInfo.getFields());
						}
						break;

					}
				}
				break;
			case SQLParser.TOK_WINDOWRANGE:
				break;
			}

		}
		return new WindowInfo(partitionFields, orderFields, ordreTypes, up, down);

	}

	private static String normalization(String origin) {
		if (origin.length() == 1) {
			return origin.toUpperCase();
		}

		if ("TOK_ISNOTNULL".equalsIgnoreCase(origin)) {
			return origin = "IsNotNULL";
		} else if ("TOK_ISNULL".equalsIgnoreCase(origin)) {
			return "IsNULL";
		}

		// very special function: CAST(f AS INTEGER)
		if (origin.startsWith("TOK_")) {
			origin = origin.substring(4);
			origin = "To_" + origin;
		}

		return StringUtil.toCamelCase(origin, false);

	}

	public static ExprInfo processExpr(QueryInfo qb, ASTNode Expr, NestContext ctx) throws SemanticException {

		String script;
		Fields fields = Fields.NONE;
		int child_count;
		ASTNode firstNode;
		ASTNode secondNode;
		ExprInfo firstExpr;
		ExprInfo secondExpr;
		ExprInfo resExpr;
		Class<?> firstType;
		Class secondType;
		Class returnType;

		switch (Expr.getToken().getType()) {
		case SQLParser.KW_OR:
			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			if (firstType != Boolean.class && secondType != Boolean.class) {
				throw new SemanticException(ErrorMsg.INVLID_TYPE_WITH_OR.getMsg(Expr));
			}

			script = BOOLEANUTIL_CLASS + ".or(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_OR, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);

		case SQLParser.KW_AND:
			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			if (firstType != Boolean.class && secondType != Boolean.class) {
				throw new SemanticException(ErrorMsg.INVLID_TYPE_WITH_AND.getMsg(Expr));
			}

			script = BOOLEANUTIL_CLASS + ".and(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_AND, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.TOK_NULL:
			script = BASEUTIL_CLASS + ".Null()";
			return new ExprInfo(script, Data.class, ExprType.NULL, fields);
		case SQLParser.TOK_FUNCTION:
			String funcName = StringUtil.unescapeIdentifier(Expr.getChild(0).getText());
			if (funcName.startsWith("_") || funcName.startsWith("$")) {
				throw new SemanticException(ErrorMsg.INVALID_FUNCTION_NAME.getMsg(Expr));
			}
			child_count = Expr.getChildCount();
			ASTNode lastExpr = (ASTNode) Expr.getChild(child_count - 1);
			WindowInfo windowinfo = processWindowSpec(lastExpr, qb, ctx);
			if (windowinfo != null) {
				if (ANALYTICAL_FUNC.contains(funcName.toUpperCase())) {
					AnalyticFuncType type = AnalyticFuncType.getType(funcName);
					String block = SCRIPT_BEFORE;
					List<Class> blockClasses = new ArrayList<Class>();
					List<String> blockList = new ArrayList<String>();
					for (int i = 1; i < child_count - 1; i++) {
						ExprInfo body = processExpr(qb, (ASTNode) Expr.getChild(1), ctx);
						blockList.add(body.getScript());
						blockClasses.add(body.getReturnType());
					}
					block += StringUtil.join(blockList.toArray(new String[blockList.size()]), ",\n\t\t");
					block += SCRIPT_AFTER;
					Class innerClasses[] = blockClasses.toArray(new Class[blockClasses.size()]);
					Fields partitionFields = windowinfo.getPartitionFields();
					Fields orderFields = windowinfo.getOrderFields();
					OrderType[] orderTypes=windowinfo.getOrderTypes().toArray(new OrderType[0]);
					String analyticalName = "$" + funcName + next();
					Class returnClass = getAnaylicalReturnType(type, innerClasses);
					Fields f = new Fields(analyticalName, returnClass);
					AnalyticFuncInfo analyticalInfo = new AnalyticFuncInfo(type, returnClass, block, innerClasses,
							partitionFields, orderFields,orderTypes, -1, -1, f);

					ExprInfo resInfo = new ExprInfo(analyticalName, returnClass, ExprType.OP_FUNC, f);
					resInfo.setFieldsAnalytical(f, analyticalInfo);
					return resInfo;
				} else
					throw new SemanticException(ErrorMsg.NO_SUPPORT_YET_FOR_FUNCTION.getMsg(Expr));

			}

			if (AGGREGATOR_FUNC.contains(funcName.toUpperCase())) {
				AggregateFuncType type = AggregateFuncType.getType(funcName);
				String block = SCRIPT_BEFORE;
				List<Class> blockClasses = new ArrayList<Class>();
				List<String> blockList = new ArrayList<String>();
				for (int i = 1; i < child_count; i++) {
					ExprInfo body = processExpr(qb, (ASTNode) Expr.getChild(1), ctx);
					blockList.add(body.getScript());
					blockClasses.add(body.getReturnType());
				}
				block += StringUtil.join(blockList.toArray(new String[blockList.size()]), ",\n\t\t");
				block += SCRIPT_AFTER;
				Class innerClasses[] = blockClasses.toArray(new Class[blockClasses.size()]);

				String analyticalName = "$" + funcName + next();
				Class returnClass = getAggregatorReturnType(type, innerClasses);
				Fields f = new Fields(analyticalName, returnClass);
				AggregateFuncInfo aggregatorinfo = new AggregateFuncInfo(type, returnClass, block, innerClasses, f);

				ExprInfo resInfo = new ExprInfo(analyticalName, returnClass, ExprType.OP_FUNC, f);
				resInfo.setFieldsAggregator(f, aggregatorinfo);
				return resInfo;
			}
			funcName = normalization(funcName);

			List<Class> paraTypeList = new ArrayList<Class>();
			script = EXPR_PACKAGE + "." + funcName + "." + "evaluate(" + "new Object[]{";

			for (int i = 1; i < Expr.getChildCount(); i++) {
				ExprInfo paraExprInfo = processExpr(qb, (ASTNode) Expr.getChild(i), ctx);
				fields = FieldsUtil.merge(fields, paraExprInfo.getFields());
				paraTypeList.add(paraExprInfo.getReturnType());
				if (i == Expr.getChildCount() - 1) {
					script += paraExprInfo.getScript();
				} else {
					script += paraExprInfo.getScript() + ",";
				}

			}
			script += "})";
			String className = EXPR_PACKAGE + "." + funcName;

			Class returntype;
			try {
				Class clazz = Class.forName(className);
				UDF instance = (UDF) clazz.newInstance();
				returntype = instance.valideParameterType(paraTypeList.toArray(new Class[paraTypeList.size()]));

			} catch (SemanticException e) {
				throw new SemanticException(e.getMessage() + " " + ErrorMsg.EXPR_FUNC_ERROR.getMsg(Expr));
			} catch (Exception e) {
				throw new SemanticException(ErrorMsg.MISSING_FUNC_DEFINE.getMsg(Expr));
			}

			return new ExprInfo(script, returntype, ExprType.OP_FUNC, fields);
		case SQLParser.DOT:
			String[] tab_col = processDotNode(Expr);
			String alias = tab_col[0];
			String col = tab_col[1];
			if (!qb.containColsForAlias(alias, col)) {
				throw new SemanticException(ErrorMsg.INCORRECT_COLUMN_NAME.getMsg(Expr));
			}
			String aliasCol = alias + DOT_ALIAS + col;
			fields = ctx.getFieldsForPipe(qb.getPipeForAlias(alias));
			firstType = (Class) fields.getType(aliasCol);

			script = aliasCol;

			fields = Fields.NONE;
			fields = fields.append(new Fields(aliasCol, firstType));

			return new ExprInfo(script, firstType, ExprType.OP_DOT, fields);

		case SQLParser.TOK_TABLE_OR_COL:
			col = StringUtil.unescapeIdentifier(Expr.getChild(0).getText().toLowerCase());
			String[] aliases = qb.guessAliasForCol(col);
			if (aliases.length == 0) {
				throw new SemanticException(ErrorMsg.INCORRECT_COLUMN_NAME.getMsg(Expr));
			} else if (aliases.length > 1) {
				throw new SemanticException(ErrorMsg.CANNOT_DECIDE_TABLE.getMsg(Expr));
			}
			alias = aliases[0];

			fields = ctx.getFieldsForPipe(qb.getPipeForAlias(alias));
			firstType = (Class) fields.getType(alias + DOT_ALIAS + col);

			script = alias + DOT_ALIAS + col;
			fields = Fields.NONE;
			fields = fields.append(new Fields(alias + DOT_ALIAS + col, firstType));

			return new ExprInfo(script, firstType, ExprType.OP_DOT, fields);
		case SQLParser.Number:
			String number = Expr.getText();
			try {
				int i = Integer.parseInt(number);
				script = BASEUTIL_CLASS + ".IntegerLiteral(";
				script += "\"" + i + "\"";
				script += ")";

				return new ExprInfo(script, INTEGER.class, ExprType.INTEGER, null);
			} catch (NumberFormatException ei) {
				try {
					long l = Long.parseLong(number);
					script = BASEUTIL_CLASS + ".BigIntLiteral(";
					script += "\"" + l + "\"";
					script += ")";
					return new ExprInfo(script, BIGINT.class, ExprType.BIGINT, null);

				} catch (NumberFormatException el) {
					try {
						BigDecimal bg = new BigDecimal(number);
						script = BASEUTIL_CLASS + ".DecimalLiteral(";
						script += "\"" + bg.toString() + "\"";
						script += ")";
						return new ExprInfo(script, DECIMAL.class, ExprType.DECIMAL, null);
					} catch (NumberFormatException eb) {
						throw new SemanticException(ErrorMsg.INVALID_MATH_NUMBER.getMsg(Expr));
					}
				}

			}

		case SQLParser.StringLiteral:
			String str = StringUtil.unescapeString(Expr.getText());
			script = BASEUTIL_CLASS + ".CharLiteral(";
			script += "\"" + str + "\"";
			script += ")";
			return new ExprInfo(script, CHAR.class, ExprType.CHAR, null);
		case SQLParser.TOK_STRINGLITERALSEQUENCE:
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_RIGHT_NOW.getMsg(Expr));
		case SQLParser.TOK_DATELITERAL:
			String strDate = StringUtil.unescapeString(Expr.getChild(0).getText());
			strDate = "\"" + strDate + "\"";
			script = BASEUTIL_CLASS + ".DateLiteral(";
			script += strDate;
			script += ")";
			return new ExprInfo(script, DATE.class, ExprType.DATE, null);
		case SQLParser.KW_TRUE:
			script = BASEUTIL_CLASS + "True" + "()";
			return new ExprInfo(script, Boolean.class, ExprType.BOOLEAN, null);

		case SQLParser.KW_FALSE:
			script = BASEUTIL_CLASS + "False" + "()";
			return new ExprInfo(script, Boolean.class, ExprType.BOOLEAN, null);
		case SQLParser.PLUS:
			child_count = Expr.getChildCount();
			if (child_count == 1) {
				firstNode = (ASTNode) Expr.getChild(0);
				firstExpr = processExpr(qb, firstNode, ctx);
				firstType = firstExpr.getReturnType();
				returnType = FLOAT.class;
				if (firstType.isAssignableFrom(NUMERIC.class)) {
					returnType = firstType;
				}
				script = MATHUTIL_CLASS + ".plus(";
				script += BASEUTIL_CLASS + ".IntegerLiteral(\"0\")" + "," + firstExpr.getScript();
				script += ")";
				resExpr = new ExprInfo(script, returnType, ExprType.OP_MINUS, fields);
				return putInfoFromOld(resExpr, firstExpr, null);
			}

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);

			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			returnType = FLOAT.class;
			if (CHAR.class.isAssignableFrom(firstType)) {
				firstType = FLOAT.class;
			}
			if (CHAR.class.isAssignableFrom(secondType)) {
				secondType = FLOAT.class;
			}

			if (NUMERIC.class.isAssignableFrom(firstType) && NUMERIC.class.isAssignableFrom(secondType)) {
				if (firstType == FLOAT.class || secondType == FLOAT.class) {
					returnType = FLOAT.class;
				} else if (firstType == DECIMAL.class || secondType == DECIMAL.class) {
					returnType = DECIMAL.class;
				} else if (firstType == BIGINT.class || secondType == BIGINT.class) {
					returnType = BIGINT.class;
				} else if (firstType == INTEGER.class || secondType == INTEGER.class) {
					returnType = INTEGER.class;
				} else {
					returnType = INTEGER.class;
				}

			} else if (DATETIME.class.isAssignableFrom(firstType)
					&& (NUMERIC.class.isAssignableFrom(secondType) || INTERVAL.class.isAssignableFrom(secondType))) {
				returnType = firstType;
			} else if (DATETIME.class.isAssignableFrom(secondType)
					&& (NUMERIC.class.isAssignableFrom(firstType) || INTERVAL.class.isAssignableFrom(firstType))) {
				returnType = secondType;
			}

			script = MATHUTIL_CLASS + ".plus(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";
			resExpr = new ExprInfo(script, returnType, ExprType.OP_PLUS, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);

		case SQLParser.MINUS:
			child_count = Expr.getChildCount();
			if (child_count == 1) {
				firstNode = (ASTNode) Expr.getChild(0);
				firstExpr = processExpr(qb, firstNode, ctx);
				firstType = firstExpr.getReturnType();
				returnType = FLOAT.class;
				if (firstType.isAssignableFrom(NUMERIC.class)) {
					returnType = firstType;
				}
				script = MATHUTIL_CLASS + ".minus(";
				script += BASEUTIL_CLASS + ".IntegerLiteral(\"0\")" + "," + firstExpr.getScript();
				script += ")";
				resExpr = new ExprInfo(script, returnType, ExprType.OP_MINUS, fields);
				return putInfoFromOld(resExpr, firstExpr, null);
			}

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);

			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			returnType = FLOAT.class;
			if (CHAR.class.isAssignableFrom(firstType)) {
				firstType = FLOAT.class;
			}
			if (CHAR.class.isAssignableFrom(secondType)) {
				secondType = FLOAT.class;
			}

			if (NUMERIC.class.isAssignableFrom(firstType) && NUMERIC.class.isAssignableFrom(secondType)) {
				if (firstType == FLOAT.class || secondType == FLOAT.class) {
					returnType = FLOAT.class;
				} else if (firstType == DECIMAL.class || secondType == DECIMAL.class) {
					returnType = DECIMAL.class;
				} else if (firstType == BIGINT.class || secondType == BIGINT.class) {
					returnType = BIGINT.class;
				} else if (firstType == INTEGER.class || secondType == INTEGER.class) {
					returnType = INTEGER.class;
				} else {
					returnType = INTEGER.class;
				}

			} else if (DATETIME.class.isAssignableFrom(firstType)
					&& (NUMERIC.class.isAssignableFrom(secondType) || INTERVAL.class.isAssignableFrom(secondType))) {
				returnType = firstType;
			}

			script = MATHUTIL_CLASS + ".minus(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";
			resExpr = new ExprInfo(script, returnType, ExprType.OP_MINUS, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.STAR: // '*'

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);

			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			returnType = FLOAT.class;
			if (CHAR.class.isAssignableFrom(firstType)) {
				firstType = FLOAT.class;
			}
			if (CHAR.class.isAssignableFrom(secondType)) {
				secondType = FLOAT.class;
			}

			if (NUMERIC.class.isAssignableFrom(firstType) && NUMERIC.class.isAssignableFrom(secondType)) {
				if (firstType == FLOAT.class || secondType == FLOAT.class) {
					returnType = FLOAT.class;
				} else if (firstType == DECIMAL.class || secondType == DECIMAL.class) {
					returnType = DECIMAL.class;
				} else if (firstType == BIGINT.class || secondType == BIGINT.class) {
					returnType = BIGINT.class;
				} else if (firstType == INTEGER.class || secondType == INTEGER.class) {
					returnType = INTEGER.class;
				} else {
					returnType = INTEGER.class;
				}

			}

			script = MATHUTIL_CLASS + ".multiply(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";
			resExpr = new ExprInfo(script, returnType, ExprType.OP_MULTIPLY, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.DIVIDE: // '/'
			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			returnType = FLOAT.class;
			if (CHAR.class.isAssignableFrom(firstType)) {
				firstType = FLOAT.class;
			}
			if (CHAR.class.isAssignableFrom(secondType)) {
				secondType = FLOAT.class;
			}

			if (NUMERIC.class.isAssignableFrom(firstType) && NUMERIC.class.isAssignableFrom(secondType)) {
				if (firstType == FLOAT.class || secondType == FLOAT.class) {
					returnType = FLOAT.class;
				} else if (firstType == DECIMAL.class || secondType == DECIMAL.class) {
					returnType = DECIMAL.class;
				} else if (firstType == BIGINT.class || secondType == BIGINT.class) {
					returnType = BIGINT.class;
				} else if (firstType == INTEGER.class || secondType == INTEGER.class) {
					returnType = INTEGER.class;
				} else {
					returnType = INTEGER.class;
				}

			}

			script = MATHUTIL_CLASS + ".divide(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";
			resExpr = new ExprInfo(script, returnType, ExprType.OP_DIVIDE, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.MOD: // 'MOD'
			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();
			returnType = FLOAT.class;
			if (CHAR.class.isAssignableFrom(firstType)) {
				firstType = FLOAT.class;
			}
			if (CHAR.class.isAssignableFrom(secondType)) {
				secondType = FLOAT.class;
			}

			if (NUMERIC.class.isAssignableFrom(firstType) && NUMERIC.class.isAssignableFrom(secondType)) {
				if (firstType == FLOAT.class || secondType == FLOAT.class) {
					returnType = FLOAT.class;
				} else if (firstType == DECIMAL.class || secondType == DECIMAL.class) {
					returnType = DECIMAL.class;
				} else if (firstType == BIGINT.class || secondType == BIGINT.class) {
					returnType = BIGINT.class;
				} else if (firstType == INTEGER.class || secondType == INTEGER.class) {
					returnType = INTEGER.class;
				} else {
					returnType = INTEGER.class;
				}
			}

			script = MATHUTIL_CLASS + ".mod(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";
			resExpr = new ExprInfo(script, returnType, ExprType.OP_MOD, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.EQUAL: // '=' or '=='

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".equal(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_EQ, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);

		case SQLParser.NOTEQUAL: // '<>' or '!='

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".notEqual(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_NE, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.LESSTHANOREQUALTO: // '<='

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".lessEqual(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_LE, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.LESSTHAN: // '<'

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".less(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_GREAT, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.GREATERTHANOREQUALTO: // '>='

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);
			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".greatEqual(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_GE, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
		case SQLParser.GREATERTHAN: // '>'

			firstNode = (ASTNode) Expr.getChild(0);
			secondNode = (ASTNode) Expr.getChild(1);
			firstExpr = processExpr(qb, firstNode, ctx);
			secondExpr = processExpr(qb, secondNode, ctx);

			fields = FieldsUtil.merge(firstExpr.getFields(), secondExpr.getFields());
			firstType = firstExpr.getReturnType();
			secondType = secondExpr.getReturnType();

			script = COMPAREUTIL_CLASS + ".great(";
			script += firstExpr.getScript() + "," + secondExpr.getScript();
			script += ")";

			resExpr = new ExprInfo(script, Boolean.class, ExprType.OP_GREAT, fields);
			return putInfoFromOld(resExpr, firstExpr, secondExpr);
			// TODO later
		case SQLParser.AMPERSAND: // '&'
		case SQLParser.TILDE: // '~'
		case SQLParser.BITWISEOR: // '|'
		case SQLParser.BITWISEXOR: // '^'
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_RIGHT_NOW.getMsg(Expr));

		default:

			break;
		}

		return new ExprInfo("", null, ExprType.OP_NOOP, Fields.NONE);

	}

	private static ExprInfo putInfoFromOld(ExprInfo newExpr, ExprInfo firstExpr, ExprInfo secExpr) {
		if (firstExpr != null) {
			for (Fields f : firstExpr.getAggregatorFields()) {
				AggregateFuncInfo info = firstExpr.getAggregatorForField(f);
				newExpr.setFieldsAggregator(f, info);
			}
			for (Fields f : firstExpr.getAnalyticalFields()) {
				AnalyticFuncInfo info = firstExpr.getAnalyticalForField(f);
				newExpr.setFieldsAnalytical(f, info);
			}

		}

		if (secExpr != null) {
			for (Fields f : secExpr.getAggregatorFields()) {
				AggregateFuncInfo info = secExpr.getAggregatorForField(f);
				newExpr.setFieldsAggregator(f, info);
			}

			for (Fields f : secExpr.getAnalyticalFields()) {
				AnalyticFuncInfo info = secExpr.getAnalyticalForField(f);
				newExpr.setFieldsAnalytical(f, info);

			}

		}

		return newExpr;
	}

	private static Class getAnaylicalReturnType(AnalyticFuncType type, Class[] innerClass) {
		switch (type) {
		case AVG:
			return DECIMAL.class;
		case SUM:
			return innerClass[0];
		case ROW_NUMBER:
			return INTEGER.class;
		default:
			return DECIMAL.class;

		}

	}

	private static Class getAggregatorReturnType(AggregateFuncType type, Class[] innerClass) {
		switch (type) {
		case AVG:
			return DECIMAL.class;

		case SUM:
			return innerClass[0];

		default:
			return DECIMAL.class;

		}

	}

}
