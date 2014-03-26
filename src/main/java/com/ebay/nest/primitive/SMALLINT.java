package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import com.ebay.nest.Data;

public class SMALLINT extends NUMERIC implements Comparable<NUMERIC>, Data {

	private static final long serialVersionUID = 681113394124155695L;
	private final Short s;
	private final Attribute attr;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = 2193214669244187340L;
		private static Attribute instance = new Attribute();

		private Attribute() {

		}

		public static Attribute getInstance() {
			return instance;
		}

		public String getFormat() {
			return "-(5)9";
		}

		public Class getJavaClass() {
			return Short.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return 5;
		}

		public int getScale() {
			return 0;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public SMALLINT(short s) {
		this.s = s;
		this.attr = Attribute.getInstance();
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public static SMALLINT valueOf(String s) {
		try {
			short sh = Short.parseShort(s);
			return new SMALLINT(sh);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public int intValue() {
		return s.intValue();
	}

	public long longValue() {
		return s.longValue();
	}

	public float floatValue() {
		return s.floatValue();
	}

	public double doubleValue() {
		return s.doubleValue();
	}

	public BigDecimal decimalValue() {
		return new BigDecimal(s);
	}

	public String toString() {
		return s.toString();
	}

//	public void writeExternal(ObjectOutput out) throws IOException {
//		out.writeShort(s);
//
//	}
//
//	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//		this.s = in.readShort();
//		this.attr = Attribute.getInstance();
//	}

}
