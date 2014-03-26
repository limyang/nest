package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

public final class INTEGER extends NUMERIC {

	private static final long serialVersionUID = 1L;
	private final Attribute attr;
	private final Integer i;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = 9108168532292387322L;
		private static Attribute instance = new Attribute();

		private Attribute() {
		}

		public static Attribute getInstance() {
			return instance;
		}

		public String getFormat() {
			return "-(10)9";
		}

		public Class getJavaClass() {
			return Integer.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return 10;
		}

		public int getScale() {
			return 0;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public INTEGER(int i) {
		this.i = i;
		this.attr = Attribute.getInstance();
	}

	public INTEGER(Integer i) {
		this.i = i;
		this.attr = Attribute.getInstance();
	}

	public static INTEGER valueOf(String s) {
		try {
			int i = Integer.parseInt(s);
			return new INTEGER(i);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public Attribute getAttribute() {
		return attr;
	}

	public double doubleValue() {

		return i.doubleValue();
	}

	public float floatValue() {

		return i.floatValue();
	}

	public int intValue() {

		return i.intValue();
	}

	public long longValue() {

		return i.longValue();
	}

	public BigDecimal decimalValue() {
		return new BigDecimal(i);
	}

	public String toString() {
		return i.toString();
	}

//	public void writeExternal(ObjectOutput out) throws IOException {
//		out.writeInt(i);
//
//	}
//
//	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		this.i = in.readInt();
//		this.attr = Attribute.getInstance();
//	}

}
