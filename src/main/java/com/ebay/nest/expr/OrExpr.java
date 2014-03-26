package com.ebay.nest.expr;

public class OrExpr extends BinaryExpr {

	private static final long serialVersionUID = 8189116306910967993L;

	public boolean visit(ExprVisitor v) {
		return v.visitOr(this);
	}

	public String getName() {
		return "Or" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
