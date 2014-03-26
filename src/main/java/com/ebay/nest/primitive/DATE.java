package com.ebay.nest.primitive;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public final class DATE extends DATETIME {

	private static final long serialVersionUID = 5963294558286751435L;
	private final Date date;
	private final Attribute attr;
	public final static SimpleDateFormat defaultformat = new SimpleDateFormat("yyyy-MM-dd");

	public final static class Attribute implements DATETIME.Attribute {

		private static final long serialVersionUID = 9213343210251664286L;
		private static ConcurrentHashMap<String, Attribute> map = new ConcurrentHashMap<String, Attribute>();
		private String format;

		private Attribute(String format) {
			this.format = format;
		}

		public static Attribute getInstance(String format) {
			String key = "" + format;
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
			return Date.class;
		}

		public boolean isNullable() {
			return false;
		}

	}

	public static Attribute ATTR() {
		return Attribute.getInstance("yyyy-MM-dd");
	}

	public static Attribute ATTR(String format) {
		return Attribute.getInstance(format);
	}

	public DATE(Date d) {
		this.date = d;
		this.attr = Attribute.getInstance("yyyy-MM-dd");

	}

	public DATE(long time) {
		this.date = new Date(time);
		this.attr = Attribute.getInstance("yyyy-MM-dd");
	}

	public DATE(long time, String format) {
		this.date = new Date(time);
		this.attr = Attribute.getInstance(format);
	}

	public DATE(Date d, String format) {
		this.date = d;
		this.attr = Attribute.getInstance(format);
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public static DATE valueOf(String time, String format) {
		try {
			// currently Teradata Data Format did not implement yet
			Date t = defaultformat.parse(time);
			return new DATE(t, format);
		} catch (ParseException e) {
			return null;
		}
	}

	public static DATE valueOf(String time) {
		return valueOf(time, "yyyy-MM-dd");
	}

	public long getTime() {
		return date.getTime();
	}

	public String toString() {
		return defaultformat.format(date);

	}

	@Override
	public int hashCode() {
		return date.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof DATE) {
			Date another = ((DATE) obj).date;
			if (date.equals(another)) {
				return true;
			}
		}
		return false;
	}

	// @Override
	// public void writeExternal(ObjectOutput out) throws IOException {
	// out.writeLong(this.getTime());
	//
	// }
	//
	// @Override
	// public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	// long time = in.readLong();
	// this.date = new Date(time);
	// this.attr = Attribute.getInstance("yyyy-MM-dd");
	//
	// }

}
