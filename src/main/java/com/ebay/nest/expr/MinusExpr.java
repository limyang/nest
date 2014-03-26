package com.ebay.nest.expr;

public class MinusExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public MinusExpr() {
		super();
	}

	public boolean visit(ExprVisitor v) {
		return v.visitMinus(this);
	}

	public String getName() {

		return "Minus" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
