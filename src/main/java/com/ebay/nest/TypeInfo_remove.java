package com.ebay.nest;

import java.io.Serializable;

import com.ebay.nest.analyzer.BaseTypeParams;

public class TypeInfo_remove implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String typeName;
	protected BaseTypeParams typeParams;

	public TypeInfo_remove() {
	}

	TypeInfo_remove(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public BaseTypeParams getTypeParams() {
		return typeParams;
	}

	public void setTypeParams(BaseTypeParams typeParams) {

		this.typeParams = typeParams;
	}

	public boolean equals(Object other) {
		return this == other;
	}

	public int hashCode() {
		return typeName.hashCode();
	}

	public String toString() {
		return typeName;
	}

}
