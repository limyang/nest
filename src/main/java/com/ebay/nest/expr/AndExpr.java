package com.ebay.nest.expr;

public class AndExpr extends BinaryExpr {

	private static final long serialVersionUID = 1839781486789654921L;

	public boolean visit(ExprVisitor v) {
		return v.visitAnd(this);
	}

	public String getName() {
		return "Add" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
