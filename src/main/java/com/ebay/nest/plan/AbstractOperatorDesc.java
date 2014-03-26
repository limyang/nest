package com.ebay.nest.plan;

public class AbstractOperatorDesc implements OperatorDesc {

	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("clone not supported");
	}
}