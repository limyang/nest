package com.ebay.nest.primitive;

import java.math.BigDecimal;

public final class BIGINT extends NUMERIC {

	private static final long serialVersionUID = 1L;
	private final Long l;
	private final Attribute attr;

	public final static class Attribute implements NUMERIC.Attribute {

		private static final long serialVersionUID = 2894231411345501115L;
		private static Attribute instance = new Attribute();

		private Attribute() {
		}

		public static Attribute getInstance() {
			return instance;
		}

		public String getFormat() {
			return "-(19)9";
		}

		public Class getJavaClass() {
			return Long.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getPrecision() {
			return 19;
		}

		public int getScale() {
			return 0;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public BIGINT(long l) {
		this.l = l;
		this.attr = Attribute.getInstance();
	}

	public static BIGINT valueOf(String s) {
		try {
			long l = Long.parseLong(s);
			return new BIGINT(l);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public Attribute getAttribute() {
		return attr;
	}

	public int intValue() {
		return l.intValue();
	}

	public long longValue() {
		return l.longValue();
	}

	public float floatValue() {
		return l.floatValue();
	}

	public double doubleValue() {
		return l.doubleValue();
	}

	public BigDecimal decimalValue() {
		return new BigDecimal(l);
	}

	@Override
	public String toString() {
		return l.toString();
	}

}
