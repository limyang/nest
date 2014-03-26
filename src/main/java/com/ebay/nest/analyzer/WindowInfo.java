package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.Pair;

import cascading.tuple.Fields;

public class WindowInfo {

	private Fields partitionFields;
	private List<Pair<Fields, ExprInfo>> orderExprs = new ArrayList<Pair<Fields, ExprInfo>>();
	private Fields orderFields = Fields.NONE;
	private List<OrderType> orderTypes;
	private int up = -1;
	private int down = -1;

	public WindowInfo(Fields partitionFields, Fields orderFields, List<OrderType> orderTypes, int up, int down) {
		this.partitionFields = partitionFields;
		this.orderFields = orderFields;
		this.up = up;
		this.down = down;
		this.orderTypes=orderTypes;
	}

	public Fields getPartitionFields() {
		return partitionFields;
	}

	public void setPartitionFields(Fields partitionFields) {
		this.partitionFields = partitionFields;
	}

	public List<Pair<Fields, ExprInfo>> getOrderExprs() {
		return orderExprs;
	}

	public void setOrderExprs(List<Pair<Fields, ExprInfo>> orderExprs) {
		this.orderExprs = orderExprs;
	}

	public Fields getOrderFields() {
		return orderFields;
	}

	public void setOrderFields(Fields orderFields) {
		this.orderFields = orderFields;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public List<OrderType> getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(List<OrderType> orderTypes) {
		this.orderTypes = orderTypes;
	}

}
