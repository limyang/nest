package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import com.ebay.nest.Data;

public class FLOAT extends NUMERIC implements Comparable<NUMERIC>, Data {

	private static final long serialVersionUID = 858854740695206278L;
	private final Double f;
	private final Attribute attr;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = -2341980225696565181L;
		private static Attribute instance = new Attribute();

		private Attribute() {

		}

		public static Attribute getInstance() {
			return instance;
		}

		public String getFormat() {
			return "-9.99999999999999E-999";
		}

		public Class getJavaClass() {
			return BigDecimal.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return -1;
		}

		public int getScale() {
			return -1;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public FLOAT(double f) {
		this.f = f;
		this.attr = Attribute.getInstance();
	}

	public FLOAT(float f) {
		this.f = (double) f;
		this.attr = Attribute.getInstance();

	}

	public static FLOAT valueOf(String s) {
		try {
			double d = Double.parseDouble(s);
			return new FLOAT(d);
		} catch (NumberFormatException e2) {
			return null;
		}
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public int intValue() {
		return f.intValue();
	}

	public long longValue() {
		return f.longValue();
	}

	public float floatValue() {
		return f.floatValue();
	}

	public double doubleValue() {
		return f.doubleValue();
	}

	public BigDecimal decimalValue() {
		return new BigDecimal(f.toString());
	}

	public String toString() {
		return f.toString();
	}

//	public void writeExternal(ObjectOutput out) throws IOException {
//		out.writeDouble(f);
//
//	}
//
//	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		this.f = in.readDouble();
//		this.attr = Attribute.getInstance();
//	}

}
