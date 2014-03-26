package com.ebay.nest.expr;

import java.io.Serializable;

import com.ebay.nest.analyzer.QueryUtil;

public class AliasCol implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String alias;
	private final String column;

	public String getAlias() {
		return alias;
	}

	public String getColumn() {
		return column;
	}

	public AliasCol(String alias, String column) {
		super();
		this.alias = alias;
		this.column = column;
	}

	public String toString() {
		return alias + QueryUtil.DOT_ALIAS + column;
	}

}
