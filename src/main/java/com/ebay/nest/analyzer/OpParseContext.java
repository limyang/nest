package com.ebay.nest.analyzer;

import java.io.Serializable;

public class OpParseContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private RowResolver rr;

	public OpParseContext() {
	}

	public OpParseContext(RowResolver rr) {
		this.rr = rr;
	}

	public RowResolver getRowResolver() {
		return rr;
	}

	public void setRowResolver(RowResolver rr) {
		this.rr = rr;
	}
}