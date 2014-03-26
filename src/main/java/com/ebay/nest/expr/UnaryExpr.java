package com.ebay.nest.expr;

import java.util.ArrayList;
import java.util.List;

public abstract class UnaryExpr extends ExprDesc {

	private static final long serialVersionUID = 2047706347355347924L;
	ExprDesc expr;
	private transient List<ExprDesc> child;

	public boolean supportsMultipleInputs() {
		return false;
	}

	public void setExpr(ExprDesc e) {
		expr = e;
	}

	public ExprDesc getExpr() {
		return expr;
	}

	public List<ExprDesc> getChildren() {
		if (child == null) {
			child = new ArrayList<ExprDesc>();
			child.add(expr);
		}

		return child;
	}

}
