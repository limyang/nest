package com.ebay.nest.expr;

public class DivideExpr extends BinaryExpr {

	private static final long serialVersionUID = -5655167105346547798L;

	public boolean visit(ExprVisitor v) {
		return v.visitDivide(this);
	}

	public String getName() {
		return "Add" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
