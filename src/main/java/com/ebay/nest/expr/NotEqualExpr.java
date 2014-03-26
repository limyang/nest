package com.ebay.nest.expr;

public class NotEqualExpr extends BinaryExpr {

	private static final long serialVersionUID = 5976062756297971025L;

	public boolean visit(ExprVisitor v) {

		return v.visitNotEqual(this);
	}

	public String getName() {

		return "NotEqual" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}
}
