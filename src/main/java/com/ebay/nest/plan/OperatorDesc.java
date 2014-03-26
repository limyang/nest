package com.ebay.nest.plan;

import java.io.Serializable;

public interface OperatorDesc extends Serializable, Cloneable {
	public Object clone() throws CloneNotSupportedException;
}
