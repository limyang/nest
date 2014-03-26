package com.ebay.nest.analyzer;

import cascading.tuple.Fields;

public class AnalyticFuncInfo {

	public OrderType[] getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(OrderType[] orderTypes) {
		this.orderTypes = orderTypes;
	}

	public Fields getPartitionFields() {
		return partitionFields;
	}

	public void setPartitionFields(Fields partitionFields) {
		this.partitionFields = partitionFields;
	}

	public Fields getOrderFields() {
		return orderFields;
	}

	public void setOrderFields(Fields orderFields) {
		this.orderFields = orderFields;
	}

	public int getUprows() {
		return uprows;
	}

	public void setUprows(int uprows) {
		this.uprows = uprows;
	}

	public int getDownrows() {
		return downrows;
	}

	public void setDownrows(int downrows) {
		this.downrows = downrows;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public Class[] getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(Class[] innerClasses) {
		this.innerClasses = innerClasses;
	}

	public AnalyticFuncType getType() {
		return type;
	}

	public void setType(AnalyticFuncType type) {
		this.type = type;
	}

	public Class getReturnClass() {
		return returnClass;
	}

	public void setReturnClass(Class returnClass) {
		this.returnClass = returnClass;
	}

	private Fields declaredFields;

	public Fields getDeclaredFields() {
		return declaredFields;
	}

	public void setDeclaredFields(Fields declaredFields) {
		this.declaredFields = declaredFields;
	}

	private Fields partitionFields;
	private Fields orderFields;
	private OrderType orderTypes[];
	private int uprows = -1;
	private int downrows = -1;
	private String block;
	private Class innerClasses[];
	private AnalyticFuncType type;
	private Class returnClass;

	public AnalyticFuncInfo(AnalyticFuncType type, Class returnClass, String block, Class[] innerClasses,
			Fields partitionFields, Fields orderFields, OrderType[] orderTypes, int uprows, int downrows, Fields declaredFields) {
		this.declaredFields = declaredFields;
		this.partitionFields = partitionFields;
		this.orderFields = orderFields;
		this.uprows = uprows;
		this.downrows = downrows;
		this.block = block;
		this.innerClasses = innerClasses;
		this.orderTypes=orderTypes;
		this.type = type;
		this.returnClass = returnClass;
	}

}
