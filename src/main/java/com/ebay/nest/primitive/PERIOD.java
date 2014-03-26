package com.ebay.nest.primitive;

import java.io.Serializable;

import com.ebay.nest.Data;

public abstract class PERIOD implements Serializable, Data {

	private static final long serialVersionUID = -3720136031567054678L;

	public static interface Attribute extends com.ebay.nest.TypeInfo {

	}

	public abstract Attribute getAttribute();

}
