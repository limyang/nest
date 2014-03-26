package com.ebay.nest.expr;

public class LessExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public LessExpr() {
		super();

	}

	public boolean visit(ExprVisitor v) {
		return v.visitLess(this);
	}

	public String getName() {

		return "Less" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
