package com.ebay.nest.expr;

import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.analyzer.AnalyticFuncType;

public class AnalyticalFuncExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;

	private List<ExprDesc> params;

	private List<AliasColExpr> partition;
	private List<OrderExpr> orders;
	private List<ExprDesc> children;

	private AnalyticFuncType type;

	public AnalyticalFuncExpr(AnalyticFuncType type, List<ExprDesc> params, List<AliasColExpr> partition,
			List<OrderExpr> orders) {
		this.type = type;
		this.params = params;
		this.partition = partition;
		this.orders = orders;
	}

	public List<ExprDesc> getParams() {
		return params;
	}

	public void setParams(List<ExprDesc> params) {
		this.params = params;
	}

	public List<AliasColExpr> getPartition() {
		return partition;
	}

	public void setPartition(List<AliasColExpr> partition) {
		this.partition = partition;
	}

	public List<OrderExpr> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderExpr> orders) {
		this.orders = orders;
	}

	public AnalyticFuncType getType() {
		return type;
	}

	public void setType(AnalyticFuncType type) {
		this.type = type;
	}

	public boolean visit(ExprVisitor v) {
		return v.visitAnalyticalFunc(this);
	}

	public List<ExprDesc> getChildren() {
		if (children == null) {
			children = new ArrayList<ExprDesc>();
			children.addAll(params);
			children.addAll(partition);
			children.addAll(orders);
		}

		return children;
	}

	public boolean supportsMultipleInputs() {
		return false;
	}

	public String getName() {
		return "Analytical Func" + "(" + type.toString() + ")" + "[" + resultType.getSimpleName() + "]" + " - "
				+ getId();
	}

}
