package com.ebay.nest.expr;

import com.ebay.nest.plan.ElementVisitor;
import com.ebay.nest.plan.Walker;

public class ExprVisitor extends ElementVisitor<ExprDesc> {

	private static final long serialVersionUID = 1L;

	protected ExprVisitor(ExprDesc op, Walker<ExprDesc> walker) {
		super(op, walker);
	}

	// /
	public boolean visitAnd(AndExpr andExpr) {
		return false;
	}

	public boolean visitOr(OrExpr orExpr) {
		return false;
	}

	public boolean visitNot(NotExpr notExpr) {
		return false;
	}

	// //
	public boolean visitPlus(PlusExpr plusExpr) {
		return false;
	}

	public boolean visitMinus(MinusExpr minusExpr) {
		return false;
	}

	public boolean visitMultiply(MultiplyExpr multiplyExpr) {
		return false;
	}

	public boolean visitDivide(DivideExpr divideExpr) {
		return false;
	}

	public boolean visitMod(ModExpr modExpr) {
		return false;
	}

	// ///
	public boolean visitEqual(EqualExpr equalExpr) {
		return false;
	}

	public boolean visitNotEqual(NotEqualExpr notEqualExpr) {
		return false;
	}

	public boolean visitLess(LessExpr lessExpr) {
		return false;
	}

	public boolean visitLessEqual(LessEqualExpr lessEqualExpr) {
		return false;
	}

	public boolean visitGreatEqualThan(GreatEqualExpr greatEqualExpr) {
		return false;
	}

	public boolean visitGreat(GreatExpr greatExpr) {
		return false;
	}

	// /////
	public boolean visitOrder(OrderExpr orderExpr) {
		return false;
	}

	public boolean visitConstant(ConstantExpr constantExpression) {
		return true;
	}

	public boolean visitColumn(AliasColExpr columnExpr) {
		return true;
	}

	// ////
	public boolean visitUDF(UDFExpr udfExpr) {
		return false;
	}

	public boolean visitAggregateFunc(AggregateFuncExpr aggregateFuncExpr) {

		return false;
	}

	public boolean visitAnalyticalFunc(AnalyticalFuncExpr analyticalFuncExpr) {
		return false;
	}

}
