package com.ebay.nest.expr;

public class LessEqualExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public LessEqualExpr() {
		super();

	}

	public boolean visit(ExprVisitor v) {
		return v.visitLessEqual(this);
	}

	public String getName() {

		return "LessEqual" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}