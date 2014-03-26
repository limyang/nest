package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Time;
import java.util.concurrent.ConcurrentHashMap;

public class TIME extends DATETIME {

	private static final long serialVersionUID = 1L;
	private final Time t;
	private final Attribute attr;

	public final static class Attribute implements DATETIME.Attribute {

		private static final long serialVersionUID = -5906354539615222664L;
		private static ConcurrentHashMap<String, Attribute> map = new ConcurrentHashMap<String, Attribute>();
		private final String format;

		private Attribute(String format) {
			this.format = format;
		}

		public static Attribute getInstance(String format) {
			String key = format;
			Attribute value = map.get(key);
			if (value == null) {
				value = new Attribute(format);
				map.putIfAbsent(key, value);
				return map.get(key);// do not change to value!!
			}
			return value;
		}

		public String getFormat() {
			return format;
		}

		public Class getJavaClass() {
			return Time.class;
		}

		public boolean isNullable() {
			return false;
		}

	}

	public static Attribute ATTR(String format) {
		return Attribute.getInstance(format);
	}

	public static Attribute ATTR() {
		return Attribute.getInstance("HH:mm:ss");
	}

	public TIME(Time t) {
		this.t = t;
		this.attr = Attribute.getInstance("HH:mm:ss");
	}

	public TIME(long l) {
		this.t = new Time(l);
		this.attr = Attribute.getInstance("HH:mm:ss");
	}

	public TIME(Time t, String format) {
		this.t = t;
		this.attr = Attribute.getInstance(format);

	}

	public static TIME valueOf(String time, String format) {
		try {
			Time t = Time.valueOf(time);
			return new TIME(t, format);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public static TIME valueOf(String time) {
		return valueOf(time, "HH:mm:ss");
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public long getTime() {
		return t.getTime();
	}

	public String toString() {
		return t.toString();
	}

	public int hashCode() {
		return t.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TIME) {
			Time another = ((TIME) obj).t;
			if (t.equals(another)) {
				return true;
			}

		}
		return false;
	}

}
