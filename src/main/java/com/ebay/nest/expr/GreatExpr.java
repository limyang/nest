package com.ebay.nest.expr;

public class GreatExpr extends BinaryExpr {

	private static final long serialVersionUID = -555741643424701043L;

	public GreatExpr() {
		super();
	}

	public boolean visit(ExprVisitor v) {
		return v.visitGreat(this);
	}

	public String getName() {
		return "Great" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
