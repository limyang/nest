package com.ebay.nest.primitive;

import java.io.Serializable;

import com.ebay.nest.Data;

public abstract class INTERVAL implements Comparable<INTERVAL>, Serializable, Data {

	private static final long serialVersionUID = -6101064220780985707L;

	public static interface Attribute extends com.ebay.nest.TypeInfo {

	}

	public abstract Attribute getAttribute();

}
