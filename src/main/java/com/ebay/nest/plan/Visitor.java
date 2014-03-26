package com.ebay.nest.plan;

import java.io.Serializable;

public abstract class Visitor implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract void visit() throws VisitorException;

}
