package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import com.ebay.nest.Data;

public class BYTEINT extends NUMERIC implements Comparable<NUMERIC>, Data {

	private static final long serialVersionUID = 1L;
	private final Byte b;
	private final Attribute attr;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = -6154434193082244518L;
		private static Attribute instance = new Attribute();

		private Attribute() {

		}

		public static Attribute getInstance() {

			return instance;
		}

		public String getFormat() {
			return "-(3)9";
		}

		public Class getJavaClass() {
			return Byte.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return 3;
		}

		public int getScale() {
			return 0;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public BYTEINT(byte b) {
		this.b = b;
		this.attr = Attribute.getInstance();
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public static BYTEINT valueOf(String s) {
		try {
			byte b = Byte.parseByte(s);
			return new BYTEINT(b);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public int intValue() {
		return b.byteValue();
	}

	public long longValue() {
		return b.longValue();
	}

	public float floatValue() {
		return b.floatValue();
	}

	public double doubleValue() {
		return b.doubleValue();
	}

	public BigDecimal decimalValue() {
		return new BigDecimal(b);
	}

	@Override
	public String toString() {
		return this.b.toString();
	}

}
