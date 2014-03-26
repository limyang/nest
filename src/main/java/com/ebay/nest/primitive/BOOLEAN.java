package com.ebay.nest.primitive;

import java.io.Serializable;

import com.ebay.nest.Data;

public class BOOLEAN implements Comparable<BOOLEAN>, Serializable, Data {

	private static final long serialVersionUID = 1L;
	public static final BOOLEAN TRUE = new BOOLEAN(true);
	public static final BOOLEAN FALSE = new BOOLEAN(false);

	private final Attribute attr;
	private final Boolean bool;

	public final static class Attribute implements com.ebay.nest.TypeInfo {

		private static final long serialVersionUID = -4395823913962549791L;

		private static Attribute instance = new Attribute();

		private Attribute() {
		}

		public static Attribute getInstance() {
			return instance;
		}

		public String getFormat() {
			return null;
		}

		public Class getJavaClass() {
			return Boolean.class;
		}

		public boolean isNullable() {
			return false;
		}

	}

	private BOOLEAN(boolean b) {
		if (b) {

			bool = Boolean.TRUE;
		} else
			bool = Boolean.FALSE;

		this.attr = Attribute.getInstance();
	}

	public static Attribute ATTR() {
		return Attribute.getInstance();
	}

	public Boolean getBoolean() {
		return bool;
	}

	public Attribute getAttribute() {

		return attr;
	}

	public int compareTo(BOOLEAN o) {
		return this.bool.compareTo(o.bool);
	}

}
