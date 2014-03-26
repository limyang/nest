package com.ebay.nest.analyzer;

import cascading.tuple.Fields;

public class AggregateFuncInfo {

	private String block;
	private Class innerClasses[];
	private AggregateFuncType type;
	private Class returnClass;
	private Fields declaredFields;

	public AggregateFuncInfo(AggregateFuncType type, Class returnClass, String block, Class[] innerClasses,
			Fields declaredFields) {

		this.block = block;
		this.innerClasses = innerClasses;
		this.type = type;
		this.returnClass = returnClass;
		this.declaredFields=declaredFields;
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

	public AggregateFuncType getType() {
		return type;
	}

	public void setType(AggregateFuncType type) {
		this.type = type;
	}

	public Class getReturnClass() {
		return returnClass;
	}

	public void setReturnClass(Class returnClass) {
		this.returnClass = returnClass;
	}

	public Fields getDeclaredFields() {
		return declaredFields;
	}

	public void setDeclaredFields(Fields declaredFields) {
		this.declaredFields = declaredFields;
	}

}
