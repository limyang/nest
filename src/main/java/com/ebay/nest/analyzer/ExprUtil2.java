package com.ebay.nest.analyzer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ebay.nest.FuncSpec;
import com.ebay.nest.expr.AggregateFuncExpr;
import com.ebay.nest.expr.AliasColExpr;
import com.ebay.nest.expr.AnalyticalFuncExpr;
import com.ebay.nest.expr.AndExpr;
import com.ebay.nest.expr.ConstantExpr;
import com.ebay.nest.expr.DivideExpr;
import com.ebay.nest.expr.EqualExpr;
import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.expr.GreatEqualExpr;
import com.ebay.nest.expr.GreatExpr;
import com.ebay.nest.expr.LessEqualExpr;
import com.ebay.nest.expr.LessExpr;
import com.ebay.nest.expr.MinusExpr;
import com.ebay.nest.expr.ModExpr;
import com.ebay.nest.expr.MultiplyExpr;
import com.ebay.nest.expr.NotEqualExpr;
import com.ebay.nest.expr.NotExpr;
import com.ebay.nest.expr.OrExpr;
import com.ebay.nest.expr.OrderExpr;
import com.ebay.nest.expr.PlusExpr;
import com.ebay.nest.expr.UDFExpr;
import com.ebay.nest.parser.ASTNode;
import com.ebay.nest.parser.SQLParser;
import com.ebay.nest.plan.NodeIdGenerator;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BOOLEAN;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.utils.StringUtil;

public class ExprUtil2 {

	public static final Set<String> AGGREGATOR_FUNC = new HashSet<String>();
	public static final Set<String> ANALYTICAL_FUNC = new HashSet<String>();

	protected static NodeIdGenerator nodeGen = NodeIdGenerator.getGenerator();

	static {
		for (AggregateFuncType value : AggregateFuncType.values()) {
			AGGREGATOR_FUNC.add(value.toString());
		}

		for (AnalyticFuncType value : AnalyticFuncType.values()) {
			ANALYTICAL_FUNC.add(value.toString());
		}
	}

	public static ExprDesc processExpr(QueryInfo qb, ASTNode Expr) throws SemanticException {

		int child_count;
		ExprDesc leftExpr;
		ExprDesc rightExpr;

		switch (Expr.getToken().getType()) {
		case SQLParser.KW_OR:
			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			OrExpr orExpr = new OrExpr();
			orExpr.setLhs(leftExpr);
			orExpr.setRhs(rightExpr);
			return orExpr;

		case SQLParser.KW_AND:
			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			AndExpr andExpr = new AndExpr();
			andExpr.setLhs(leftExpr);
			andExpr.setRhs(rightExpr);
			return andExpr;
		case SQLParser.KW_NOT:
			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			NotExpr notExpr = new NotExpr();
			notExpr.setExpr(leftExpr);
			return notExpr;
		case SQLParser.TOK_NULL:
			ConstantExpr nullExpr = new ConstantExpr();
			nullExpr.setValue(null);
			return nullExpr;
		case SQLParser.Number:
			String number = Expr.getText();
			ConstantExpr NumberExpr = new ConstantExpr();
			try {
				int i = Integer.parseInt(number);
				NumberExpr.setValue(new INTEGER(i));
			} catch (NumberFormatException ei) {
				try {
					long l = Long.parseLong(number);
					NumberExpr.setValue(new BIGINT(l));
				} catch (NumberFormatException el) {
					try {
						BigDecimal bd = new BigDecimal(number);
						NumberExpr.setValue(new DECIMAL(bd));
					} catch (NumberFormatException eb) {
						throw new SemanticException(ErrorMsg.INVALID_MATH_NUMBER.getMsg(Expr));
					}
				}

			}
			return NumberExpr;

		case SQLParser.StringLiteral:
			String string = StringUtil.unescapeString(Expr.getText());
			ConstantExpr stringExpr = new ConstantExpr();
			stringExpr.setValue(new CHAR(string));

			return stringExpr;
		case SQLParser.TOK_STRINGLITERALSEQUENCE:
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_RIGHT_NOW.getMsg(Expr));
		case SQLParser.TOK_DATELITERAL:
			String strDate = StringUtil.unescapeString(Expr.getChild(0).getText());
			ConstantExpr dateExpr = new ConstantExpr();
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				DATE date = new DATE(formatter.parse(strDate));
				dateExpr.setValue(date);
			} catch (ParseException e) {
				throw new SemanticException(ErrorMsg.INVALID_MATH_NUMBER.getMsg(Expr));
			}

			return dateExpr;
		case SQLParser.KW_TRUE:
			ConstantExpr trueExpr = new ConstantExpr();
			trueExpr.setValue(BOOLEAN.TRUE);

			return trueExpr;

		case SQLParser.KW_FALSE:
			ConstantExpr falseExpr = new ConstantExpr();
			falseExpr.setValue(BOOLEAN.FALSE);
			return falseExpr;
		case SQLParser.DOT:
			String[] tab_col = QueryUtil.processDotNode(Expr);
			String alias = tab_col[0];
			String col = tab_col[1];
			if (!qb.containColsForAlias(alias, col)) {
				throw new SemanticException(ErrorMsg.INCORRECT_COLUMN_NAME.getMsg(Expr));
			}
			AliasColExpr columnExpr = new AliasColExpr(alias, col);

			return columnExpr;

		case SQLParser.TOK_TABLE_OR_COL:
			col = StringUtil.unescapeIdentifier(Expr.getChild(0).getText().toLowerCase());
			String[] aliases = qb.guessAliasForCol(col);
			if (aliases.length == 0) {
				throw new SemanticException(ErrorMsg.INCORRECT_COLUMN_NAME.getMsg(Expr));
			} else if (aliases.length > 1) {
				throw new SemanticException(ErrorMsg.CANNOT_DECIDE_TABLE.getMsg(Expr));
			}
			alias = aliases[0];

			columnExpr = new AliasColExpr(alias, col);

			return columnExpr;
		case SQLParser.TOK_FUNCTION:
			String funcName = StringUtil.unescapeIdentifier(Expr.getChild(0).getText());
			if (funcName.startsWith("_") || funcName.startsWith("$")) {
				throw new SemanticException(ErrorMsg.INVALID_FUNCTION_NAME.getMsg(Expr));
			}
			child_count = Expr.getChildCount();
			ASTNode lastNode = (ASTNode) Expr.getChild(child_count - 1);
			WindowInfo windowinfo = processWindowSpec(qb, lastNode);
			if (windowinfo != null) {
				if (ANALYTICAL_FUNC.contains(funcName.toUpperCase())) {
					AnalyticFuncType type = AnalyticFuncType.getType(funcName);
					List<ExprDesc> params = new ArrayList<ExprDesc>();
					Class<?>[] paramClasses = new Class<?>[child_count - 2];
					for (int i = 1; i < child_count - 1; i++) {
						ExprDesc param = processExpr(qb, (ASTNode) Expr.getChild(i));
						params.add(param);
						paramClasses[i - 1] = param.getResType();
					}

					AnalyticalFuncExpr expr = new AnalyticalFuncExpr(type, params, windowinfo.getPartitions(),
							windowinfo.getOrders());
					return expr;

				} else
					throw new SemanticException(ErrorMsg.NO_SUPPORT_YET_FOR_FUNCTION.getMsg(Expr));

			}

			if (AGGREGATOR_FUNC.contains(funcName.toUpperCase())) {
				AggregateFuncType type = AggregateFuncType.getType(funcName);

				List<ExprDesc> params = new ArrayList<ExprDesc>();
				for (int i = 1; i < child_count; i++) {
					ExprDesc param = processExpr(qb, (ASTNode) Expr.getChild(i));
					params.add(param);
				}

				AggregateFuncExpr expr = new AggregateFuncExpr(type, params);
				return expr;
			}

			funcName = normalization(funcName);
			FuncSpec fs = new FuncSpec(funcName);

			List<ExprDesc> paramExprs = new ArrayList<ExprDesc>();
			for (int i = 1; i < Expr.getChildCount(); i++) {
				ExprDesc paramExpr = processExpr(qb, (ASTNode) Expr.getChild(i));
				paramExprs.add(paramExpr);
			}

			UDFExpr udfExpr = new UDFExpr(paramExprs, fs);

			return udfExpr;

		case SQLParser.PLUS:
			child_count = Expr.getChildCount();
			if (child_count == 1) {
				leftExpr = new ConstantExpr();
				((ConstantExpr) leftExpr).setValue(new INTEGER(0));
				rightExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			} else {
				leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
				rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			}

			PlusExpr plusExpr = new PlusExpr();
			plusExpr.setLhs(leftExpr);
			plusExpr.setRhs(rightExpr);
			return plusExpr;

		case SQLParser.MINUS:
			child_count = Expr.getChildCount();
			if (child_count == 1) {
				leftExpr = new ConstantExpr();
				((ConstantExpr) leftExpr).setValue(new INTEGER(0));
				rightExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			} else {
				leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
				rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			}

			MinusExpr minusExpr = new MinusExpr();
			minusExpr.setLhs(leftExpr);
			minusExpr.setRhs(rightExpr);
			return minusExpr;
		case SQLParser.STAR: // '*'

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));

			MultiplyExpr multiExpr = new MultiplyExpr();
			multiExpr.setLhs(leftExpr);
			multiExpr.setRhs(rightExpr);

			return multiExpr;
		case SQLParser.DIVIDE: // '/'

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));

			DivideExpr divideExpr = new DivideExpr();
			divideExpr.setLhs(leftExpr);
			divideExpr.setRhs(rightExpr);
			return divideExpr;
		case SQLParser.MOD: // 'MOD'

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));

			ModExpr modExpr = new ModExpr();
			modExpr.setLhs(leftExpr);
			modExpr.setRhs(rightExpr);

			return modExpr;
		case SQLParser.EQUAL: // '=' or '=='

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			EqualExpr equalExpr = new EqualExpr();
			equalExpr.setLhs(leftExpr);
			equalExpr.setRhs(rightExpr);

			return equalExpr;

		case SQLParser.NOTEQUAL: // '<>' or '!='

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			NotEqualExpr notEqualExpr = new NotEqualExpr();
			notEqualExpr.setLhs(leftExpr);
			notEqualExpr.setRhs(rightExpr);

			return notEqualExpr;
		case SQLParser.LESSTHANOREQUALTO: // '<='

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));

			LessEqualExpr lessEqualExpr = new LessEqualExpr();
			lessEqualExpr.setLhs(leftExpr);
			lessEqualExpr.setRhs(rightExpr);

			return lessEqualExpr;
		case SQLParser.LESSTHAN: // '<'

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			LessExpr lessExpr = new LessExpr();
			lessExpr.setLhs(leftExpr);
			lessExpr.setRhs(rightExpr);

			return lessExpr;
		case SQLParser.GREATERTHANOREQUALTO: // '>='

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			GreatEqualExpr greatEqualExpr = new GreatEqualExpr();
			greatEqualExpr.setLhs(leftExpr);
			greatEqualExpr.setRhs(rightExpr);

			return greatEqualExpr;
		case SQLParser.GREATERTHAN: // '>'

			leftExpr = processExpr(qb, (ASTNode) Expr.getChild(0));
			rightExpr = processExpr(qb, (ASTNode) Expr.getChild(1));
			GreatExpr greatExpr = new GreatExpr();
			greatExpr.setLhs(leftExpr);
			greatExpr.setRhs(rightExpr);

			return greatExpr;

		case SQLParser.AMPERSAND: // '&'
		case SQLParser.TILDE: // '~'
		case SQLParser.BITWISEOR: // '|'
		case SQLParser.BITWISEXOR: // '^'
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_RIGHT_NOW.getMsg(Expr));

		default:
			throw new SemanticException(ErrorMsg.NOT_SUPPORT_RIGHT_NOW.getMsg(Expr));

		}

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

	private static WindowInfo processWindowSpec(QueryInfo qb, ASTNode ast) throws SemanticException {
		if (ast.getToken().getType() != SQLParser.TOK_WINDOWSPEC) {
			return null;
		}
		List<AliasColExpr> partitions = new ArrayList<AliasColExpr>();
		List<OrderExpr> orders = new ArrayList<OrderExpr>();

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
							ASTNode node = (ASTNode) past.getChild(k);
							if (node.getToken().getType() != SQLParser.DOT
									&& node.getToken().getType() != SQLParser.TOK_TABLE_OR_COL) {
								throw new SemanticException(ErrorMsg.INVALID_PARTIIONBY_CLAUSE.getMsg(node));
							}
							AliasColExpr expr = (AliasColExpr) ExprUtil2.processExpr(qb, node);
							partitions.add(expr);
						}
						break;
					case SQLParser.TOK_ORDERBY:
						for (int k = 0; k < past.getChildCount(); k++) {
							ASTNode order = (ASTNode) past.getChild(k);
							ASTNode node = (ASTNode) order.getChild(0);

							if (node.getToken().getType() != SQLParser.DOT
									&& node.getToken().getType() != SQLParser.TOK_TABLE_OR_COL) {
								throw new SemanticException(ErrorMsg.INVALID_PARTIIONBY_CLAUSE.getMsg(node));
							}

							AliasColExpr expr = (AliasColExpr) ExprUtil2.processExpr(qb, node);
							OrderType orderType = OrderType.ASC;
							if (order.getToken().getType() == SQLParser.TOK_TABSORTCOLNAMEASC) {
								orderType = OrderType.ASC;
							} else {
								orderType = OrderType.DESC;
							}

							OrderExpr orderExpr = new OrderExpr(expr, orderType);

							orders.add(orderExpr);
						}
						break;

					}
				}
				break;
			case SQLParser.TOK_WINDOWRANGE:
				break;
			}

		}
		return new WindowInfo(partitions, orders, up, down);

	}

	public static class WindowInfo {

		private List<AliasColExpr> partitions;
		private List<OrderExpr> orders;
		private int up = -1;
		private int down = -1;

		public WindowInfo(List<AliasColExpr> partitions, List<OrderExpr> orders, int up, int down) {
			this.partitions = partitions;
			this.orders = orders;
			this.up = up;
			this.down = down;
		}

		public List<AliasColExpr> getPartitions() {
			return partitions;
		}

		public void setPartitions(List<AliasColExpr> partitions) {
			this.partitions = partitions;
		}

		public List<OrderExpr> getOrders() {
			return orders;
		}

		public void setOrders(List<OrderExpr> orders) {
			this.orders = orders;
		}

		public int getUp() {
			return up;
		}

		public void setUp(int up) {
			this.up = up;
		}

		public int getDown() {
			return down;
		}

		public void setDown(int down) {
			this.down = down;
		}

	}

}
