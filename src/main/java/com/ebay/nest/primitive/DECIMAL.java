package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

import com.ebay.nest.Data;

public class DECIMAL extends NUMERIC implements Comparable<NUMERIC>, Data {

	private static final long serialVersionUID = 1L;
	private BigDecimal bd;
	private Attribute attr;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = -2520254962926810295L;
		private static ConcurrentHashMap<String, Attribute> map = new ConcurrentHashMap<String, Attribute>();
		private final int precision;
		private final int scale;

		private Attribute(int m, int n) {
			this.precision = m;
			this.scale = n;
		}

		public static Attribute getInstance(int m, int n) {
			String key = "" + m + ":" + n;
			Attribute value = map.get(key);
			if (value == null) {
				value = new Attribute(m, n);
				map.putIfAbsent(key, value);
				return map.get(key);// do not change to value!!
			}
			return value;
		}

		public String getFormat() {
			return "-(" + (precision - scale) + ").9(" + scale + ")";
		}

		public Class getJavaClass() {
			return BigDecimal.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return precision;
		}

		public int getScale() {
			return scale;
		}

	}

	public static Attribute ATTR(int m, int n) {
		return Attribute.getInstance(m, n);
	}

	public static Attribute ATTR(int m) {
		return Attribute.getInstance(m, 0);
	}

	public static Attribute ATTR() {
		return Attribute.getInstance(5, 0);
	}

	public DECIMAL() {

	}

	public DECIMAL(BigDecimal bd) {
		this.bd = bd;

		this.attr = ATTR(bd.precision(), bd.scale());
	}

	public DECIMAL(BigDecimal bd, int scale) {
		if (bd == null) {
			throw new IllegalArgumentException("expected BigDecimal should not be null");
		}
		try {
			this.bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		} catch (OutOfMemoryError e) {
			// do nothing
		}
		this.attr = ATTR(bd.precision(), bd.scale());
	}

	public DECIMAL(BigDecimal bd, int precision, int scale) {
		if (bd == null) {
			throw new IllegalArgumentException("expected BigDecimal should not be null");
		}
		try {
			this.bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		} catch (OutOfMemoryError e) {
			// do nothing
		}
		if (bd.precision() > precision) {
			throw new RuntimeException(bd.toString() + " cannot cast to precision " + precision);
		}
		this.attr = ATTR(precision, bd.scale());
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public static DECIMAL valueOf(String s) {
		try {
			BigDecimal bd = new BigDecimal(s);
			return new DECIMAL(bd);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static DECIMAL valueOf(String s, int scale) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(s);
		} catch (NumberFormatException e) {
			return null;
		}
		try {
			bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		} catch (OutOfMemoryError e) {
			// Big Decimal cannot handle very big data, such has 10E600000000, in which case, we just ignore;
			// do nothing
		}
		return new DECIMAL(bd);

	}

	public int intValue() {
		return bd.intValue();
	}

	public long longValue() {
		return bd.longValue();
	}

	public float floatValue() {
		return bd.floatValue();
	}

	public double doubleValue() {
		return bd.doubleValue();
	}

	public BigDecimal decimalValue() {
		return this.bd;
	}

	public String toString() {

		return this.bd.toString();
	}

//	public void writeExternal(ObjectOutput out) throws IOException {
//		BigInteger value = this.bd.unscaledValue();
//		byte[] valueBytes = value.toByteArray();
//
//		out.writeInt(valueBytes.length);
//		out.write(valueBytes);
//		out.writeInt(this.bd.scale());
//
//	}
//
//	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		int len = in.readInt();
//		byte[] valueBytes = new byte[len];
//
//		in.readFully(valueBytes);
//		BigInteger value = new BigInteger(valueBytes);
//		this.bd = new BigDecimal(value, in.readInt());
//		int pre = bd.precision();
//		int scale = bd.scale();
//		this.attr = Attribute.getInstance(pre, scale);
//	}

}
