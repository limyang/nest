package com.ebay.nest.expr;

import java.util.List;

import com.ebay.nest.analyzer.AggregateFuncType;

public class AggregateFuncExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;

	private AggregateFuncType type;
	private List<ExprDesc> params;

	public AggregateFuncExpr(AggregateFuncType type, List<ExprDesc> params) {
		this.type = type;
		this.params = params;
	}

	public List<ExprDesc> getParams() {
		return params;
	}

	public void setParams(List<ExprDesc> params) {
		this.params = params;
	}

	public AggregateFuncType getType() {
		return type;
	}

	public void setType(AggregateFuncType type) {
		this.type = type;
	}

	public boolean visit(ExprVisitor v) {
		return v.visitAggregateFunc(this);
	}

	public List<ExprDesc> getChildren() {
		return params;
	}

	public boolean supportsMultipleInputs() {
		return true;
	}

	public String getName() {
		return "Aggregate Func" + "(" + type.toString() + ")" + "[" + resultType.getSimpleName() + "]" + " - "
				+ getId();
	}

}
