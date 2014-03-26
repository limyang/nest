package com.ebay.nest.primitive;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ebay.nest.Data;

public abstract class NUMERIC implements Comparable<NUMERIC>, Serializable, Data {

	private static final long serialVersionUID = 9121194319057954193L;

	public static interface Attribute extends com.ebay.nest.TypeInfo {

		public int getPrecision();

		public int getScale();
	}

	public abstract Attribute getAttribute();

	public abstract int intValue();

	public abstract long longValue();

	public abstract BigDecimal decimalValue();

	public abstract float floatValue();

	public abstract double doubleValue();

	public byte byteValue() {

		return (byte) intValue();

	}

	public short shortValue() {

		return (short) intValue();

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NUMERIC) {
			BigDecimal bd1 = decimalValue();
			BigDecimal bd2 = ((NUMERIC) obj).decimalValue();
			return bd1.equals(bd2);
		}
		return false;

	}

	@Override
	public int hashCode() {
		BigDecimal bd = decimalValue();
		if (bd == null) {
			return 0;
		}
		return bd.hashCode();
	}

	@Override
	public int compareTo(NUMERIC other) {
		BigDecimal bd1 = this.decimalValue();
		if (other == null) {
			return 1;
		}
		BigDecimal bd2 = other.decimalValue();
		return bd1.compareTo(bd2);
	}

}
