package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

public class TIMESTAMP extends DATETIME {

	private static final long serialVersionUID = 1L;
	private final Timestamp t;
	private final Attribute attr;
	public static SimpleDateFormat defaultformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public final static class Attribute implements DATETIME.Attribute {

		private static final long serialVersionUID = -7812297349009113470L;
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

		@Override
		public String getFormat() {
			return format;
		}

		@Override
		public Class getJavaClass() {
			return Timestamp.class;
		}

		@Override
		public boolean isNullable() {
			return false;
		}

	}

	public static Attribute ATTR(String format) {
		return Attribute.getInstance(format);
	}

	public static Attribute ATTR() {
		return Attribute.getInstance("yyyy-MM-dd HH:mm:ss");
	}

	public TIMESTAMP(long l) {
		this.t = new Timestamp(l);
		this.attr = Attribute.getInstance("yyyy-MM-dd HH:mm:ss");
	}

	public TIMESTAMP(Timestamp t) {
		this.t = t;
		this.attr = Attribute.getInstance("yyyy-MM-dd HH:mm:ss");
	}

	public TIMESTAMP(Timestamp t, String format) {
		this.t = t;
		this.attr = Attribute.getInstance(format);

	}

	public static TIMESTAMP valueOf(String time) {
		return valueOf(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static TIMESTAMP valueOf(String time, String format) {
		try {
			Timestamp t = Timestamp.valueOf(time);
			return new TIMESTAMP(t, format);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public long getTime() {

		return t.getTime();
	}

	public String toString() {
		return defaultformat.format(t);
	}

	public int hashCode() {
		return t.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof TIMESTAMP) {
			Timestamp another = ((TIMESTAMP) obj).t;
			if (t.equals(another)) {
				return true;
			}

		}
		return false;
	}

	// public void writeExternal(ObjectOutput out) throws IOException {
	// out.writeLong(t.getTime());
	// }
	//
	// public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	// long time = in.readLong();
	// this.t = new Timestamp(time);
	// this.attr = Attribute.getInstance("yyyy-MM-dd HH:mm:ss");
	//
	// }

}
