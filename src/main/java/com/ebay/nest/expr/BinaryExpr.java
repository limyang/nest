package com.ebay.nest.expr;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;

	protected ExprDesc lhs;
	protected ExprDesc rhs;
	private transient List<ExprDesc> child;

	public List<ExprDesc> getChildren() {
		if (child == null) {
			child = new ArrayList<ExprDesc>();
			child.add(lhs);
			child.add(rhs);
		}
		return child;
	}

	public boolean supportsMultipleInputs() {
		return true;
	}

	public void setLhs(ExprDesc lhs) {
		this.lhs = lhs;
	}

	public ExprDesc getRhs() {
		return rhs;
	}

	public void setRhs(ExprDesc rhs) {
		this.rhs = rhs;
	}

	public ExprDesc getLhs() {
		return lhs;
	}

}
