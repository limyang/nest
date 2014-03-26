package com.ebay.nest.analyzer;

import java.io.Serializable;
import java.util.ArrayList;

public class RowSchema implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<ColumnInfo> signature;

	public RowSchema() {
	}

	public RowSchema(RowSchema that) {
		this.signature = (ArrayList<ColumnInfo>) that.signature.clone();
	}

	public RowSchema(ArrayList<ColumnInfo> signature) {
		this.signature = signature;
	}

	public void setSignature(ArrayList<ColumnInfo> signature) {
		this.signature = signature;
	}

	public ArrayList<ColumnInfo> getSignature() {
		return signature;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder('(');
		for (ColumnInfo col : signature) {
			sb.append(col.toString());
		}
		sb.append(')');
		return sb.toString();
	}
}
