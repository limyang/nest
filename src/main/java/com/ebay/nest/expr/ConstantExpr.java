package com.ebay.nest.expr;

import java.util.List;

import com.ebay.nest.Data;

public class ConstantExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;

	Data value;

	public String getName() {
		if (value != null) {
			return "Constant(" + value.toString() + ")" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
		} else {
			return "Constant(" + "DummyVal" + ")" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
		}
	}

	public boolean supportsMultipleInputs() {
		return false;
	}

	public boolean supportsMultipleOutputs() {
		return false;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Data value) {
		this.value = value;

	}

	public List<ExprDesc> getChildren() {
		return null;
	}

	public boolean visit(ExprVisitor v) {
		return v.visitConstant(this);

	}

}
