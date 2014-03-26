package com.ebay.nest.expr;

public class NotExpr extends UnaryExpr {

	private static final long serialVersionUID = 1L;

	public boolean visit(ExprVisitor v) {
		return v.visitNot(this);
	}

	public String getName() {
		return "Not" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
