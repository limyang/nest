package com.ebay.nest.plan;

import java.io.Serializable;

public abstract class Walker<O extends Element> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected O op;

	public Walker(O op) {
		this.op = op;
	}

	public abstract void walk(ElementVisitor<O> visitor);

}
