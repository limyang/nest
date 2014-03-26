package com.ebay.nest.expr;

import java.io.IOException;
import java.util.List;

import cascading.tuple.TupleEntry;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.arithmetic.CompareUtil;
import com.ebay.nest.arithmetic.MathUtil;
import com.ebay.nest.primitive.BOOLEAN;

public class TupleExprEval extends ExprVisitor {

	private static final long serialVersionUID = 1L;
	TupleEntry entry;
	Data lastRes;

	public TupleExprEval(ExprDesc op) {
		super(op, new ExprDepthLastWalker(op));
	}

	public Data compute(TupleEntry entry) {
		lastRes = null;
		this.entry = entry;
		visit();
		return lastRes;

	}

	public boolean visitAnd(AndExpr andExpr) {
		andExpr.getLhs().visit(this);
		BOOLEAN left = CastUtil.toBoolean(lastRes);
		andExpr.getRhs().visit(this);
		BOOLEAN right = CastUtil.toBoolean(lastRes);
		if (left == null || right == null) {
			lastRes = BOOLEAN.FALSE;
		}
		boolean l = left.getBoolean();
		boolean r = right.getBoolean();
		if (l && r) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitOr(OrExpr orExpr) {
		orExpr.getLhs().visit(this);
		BOOLEAN left = CastUtil.toBoolean(lastRes);
		orExpr.getRhs().visit(this);
		BOOLEAN right = CastUtil.toBoolean(lastRes);
		if (left == null || right == null) {
			lastRes = BOOLEAN.FALSE;
		}
		boolean l = left.getBoolean();
		boolean r = right.getBoolean();
		if (l || r) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitNot(NotExpr notExpr) {
		notExpr.getExpr().visit(this);
		BOOLEAN data = CastUtil.toBoolean(lastRes);
		boolean l = data.getBoolean();
		if (l) {
			lastRes = BOOLEAN.FALSE;
		} else {
			lastRes = BOOLEAN.TRUE;
		}
		return true;
	}

	// //
	public boolean visitPlus(PlusExpr plusExpr) {
		plusExpr.getLhs().visit(this);
		Data left = lastRes;
		plusExpr.getRhs().visit(this);
		Data right = lastRes;
		lastRes = (Data) MathUtil.plus(left, right);

		return true;
	}

	public boolean visitMinus(MinusExpr minusExpr) {
		minusExpr.getLhs().visit(this);
		Data left = lastRes;
		minusExpr.getRhs().visit(this);
		Data right = lastRes;
		lastRes = (Data) MathUtil.minus(left, right);

		return true;
	}

	public boolean visitMultiply(MultiplyExpr multiplyExpr) {
		multiplyExpr.getLhs().visit(this);
		Data left = lastRes;
		multiplyExpr.getRhs().visit(this);
		Data right = lastRes;
		lastRes = (Data) MathUtil.multiply(left, right);

		return true;
	}

	public boolean visitDivide(DivideExpr divideExpr) {
		divideExpr.getLhs().visit(this);
		Data left = lastRes;
		divideExpr.getRhs().visit(this);
		Data right = lastRes;
		lastRes = (Data) MathUtil.divide(left, right);

		return true;
	}

	public boolean visitMod(ModExpr modExpr) {
		modExpr.getLhs().visit(this);
		Data left = lastRes;
		modExpr.getRhs().visit(this);
		Data right = lastRes;
		lastRes = (Data) MathUtil.mod(left, right);
		return true;
	}

	// ///
	public boolean visitEqual(EqualExpr equalExpr) {
		equalExpr.getLhs().visit(this);
		Data left = lastRes;
		equalExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.equal(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitNotEqual(NotEqualExpr notEqualExpr) {
		notEqualExpr.getLhs().visit(this);
		Data left = lastRes;
		notEqualExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.notEqual(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitLess(LessExpr lessExpr) {
		lessExpr.getLhs().visit(this);
		Data left = lastRes;
		lessExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.less(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitLessEqual(LessEqualExpr lessEqualExpr) {
		lessEqualExpr.getLhs().visit(this);
		Data left = lastRes;
		lessEqualExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.lessEqual(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitGreatEqualThan(GreatEqualExpr greatEqualExpr) {
		greatEqualExpr.getLhs().visit(this);
		Data left = lastRes;
		greatEqualExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.greatEqual(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	public boolean visitGreat(GreatExpr greatExpr) {
		greatExpr.getLhs().visit(this);
		Data left = lastRes;
		greatExpr.getRhs().visit(this);
		Data right = lastRes;
		if (CompareUtil.great(left, right)) {
			lastRes = BOOLEAN.TRUE;
		} else {
			lastRes = BOOLEAN.FALSE;
		}

		return true;
	}

	// /////
	public boolean visitOrder(OrderExpr orderExpr) {
		return true;
	}

	public boolean visitConstant(ConstantExpr constantExpression) {
		lastRes = (Data) constantExpression.getValue();
		return true;
	}

	public boolean visitColumn(AliasColExpr columnExpr) {
		AliasCol aliasCol = columnExpr.getAliasCol();

		lastRes = (Data) entry.getObject(aliasCol.toString());
		return true;
	}

	// ////
	public boolean visitUDF(UDFExpr udfExpr) {
		List<ExprDesc> params = udfExpr.getParams();
		Data[] inputs = new Data[params.size()];
		for (int i = 0; i < params.size(); i++) {
			params.get(i).visit(this);
			inputs[i] = lastRes;
		}
		UDF udf = udfExpr.getFunc();
		try {
			lastRes = udf.exec(inputs);
		} catch (IOException e) {
			lastRes = null;
		}

		return true;
	}

	public boolean visitAggregateFunc(AggregateFuncExpr aggregateFuncExpr) {

		return true;
	}

	public boolean visitAnalyticalFunc(AnalyticalFuncExpr analyticalFuncExpr) {
		return true;
	}

}
