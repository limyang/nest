package com.ebay.nest.expr;

public class PlusExpr extends BinaryExpr {

	private static final long serialVersionUID = 1L;

	public boolean visit(ExprVisitor v) {
		return v.visitPlus(this);
	}

	public String getName() {
		return "Plus" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
