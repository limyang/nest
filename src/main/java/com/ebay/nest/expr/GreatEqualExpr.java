package com.ebay.nest.expr;

public class GreatEqualExpr extends BinaryExpr {

	private static final long serialVersionUID = -555741643424701043L;

	public GreatEqualExpr() {
		super();
	}

	public boolean visit(ExprVisitor v) {
		return v.visitGreatEqualThan(this);
	}

	public String getName() {
		return "GreatEqual" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}