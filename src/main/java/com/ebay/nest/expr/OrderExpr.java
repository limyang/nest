package com.ebay.nest.expr;

import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.analyzer.OrderType;

public class OrderExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;
	private OrderType orderType;

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	private ExprDesc expr;
	private List<ExprDesc> children;

	public ExprDesc getExpr() {
		return expr;
	}

	public void setExpr(ExprDesc expr) {
		this.expr = expr;
	}

	public OrderExpr(ExprDesc expr, OrderType orderType) {
		this.expr = expr;
		this.orderType = orderType;
	}

	public boolean visit(ExprVisitor v) {
		return v.visitOrder(this);
	}

	public List<ExprDesc> getChildren() {
		if (children == null) {
			children = new ArrayList<ExprDesc>();
			children.add(expr);
		}
		return children;
	}

	public boolean supportsMultipleInputs() {
		return false;
	}

	public String getName() {
		return "UDF" + "(" + expr.getName() + ", " + orderType.toString() + ")" + "[" + resultType.getSimpleName() + "]"
				+ " - " + getId();
	}

}
