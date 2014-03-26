package com.ebay.nest.expr;

public class MultiplyExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public boolean visit(ExprVisitor v) {
		return v.visitMultiply(this);
	}

	public String getName() {

		return "Multiply" + "[" + resultType.getSimpleName() + "]" + " - " +getId();
	}

}
