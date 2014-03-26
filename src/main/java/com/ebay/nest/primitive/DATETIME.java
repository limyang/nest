package com.ebay.nest.primitive;

import java.io.Serializable;

import com.ebay.nest.Data;

public abstract class DATETIME implements Comparable<DATETIME>, Serializable, Data {

	private static final long serialVersionUID = -8007356163097485411L;

	public static interface Attribute extends com.ebay.nest.TypeInfo {

	}

	public abstract Attribute getAttribute();

	public abstract long getTime();

	public int compareTo(DATETIME other) {
		long t1 = this.getTime();
		long t2 = other.getTime();
		return (t1 > t2) ? 1 : (t1 < t2) ? -1 : 0;
	}

	@Override
	public boolean equals(Object obj) {
		DATETIME other = (DATETIME) obj;
		return this.compareTo(other) == 0;
	}

	@Override
	public int hashCode() {
		return (int) (this.getTime() % Integer.MAX_VALUE);
	}

}
