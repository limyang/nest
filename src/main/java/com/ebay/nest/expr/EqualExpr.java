package com.ebay.nest.expr;

public class EqualExpr extends BinaryExpr {

	private static final long serialVersionUID = 5976062756297971025L;

	public boolean visit(ExprVisitor v) {

		return v.visitEqual(this);
	}

	public String getName() {

		return "Equal" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
