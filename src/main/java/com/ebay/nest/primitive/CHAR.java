package com.ebay.nest.primitive;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.concurrent.ConcurrentHashMap;

import com.ebay.nest.Data;

public final class CHAR implements Comparable<CHAR>, Data {

	private static final long serialVersionUID = 1L;

	private final Attribute attr;

	private final String string;

	public final static class Attribute implements com.ebay.nest.TypeInfo {

		private static final long serialVersionUID = -5119369849517852896L;
		private static ConcurrentHashMap<String, Attribute> map = new ConcurrentHashMap<String, Attribute>();
		private int n;
		private boolean isCaseSensitive;
		private boolean isFixed;

		private Attribute(int n, boolean isCase, boolean isFixed) {
			this.n = n;
			this.isCaseSensitive = isCase;
			this.isFixed = isFixed;
		}

		public static Attribute getInstance(int n, boolean isCase, boolean isFixed) {
			String key = "" + n + ":" + isCase + ":" + isFixed;
			Attribute value = map.get(key);
			if (value == null) {
				value = new Attribute(n, isCase, isFixed);
				map.putIfAbsent(key, value);
				return map.get(key);// do not change to value!!
			}
			return value;
		}

		public Class getJavaClass() {
			return String.class;
		}

		public boolean isNullable() {
			return false;
		}

		public boolean isCaseSensitive() {
			return this.isCaseSensitive;
		}

		public String getFormat() {
			return "X(" + n + ")";
		}

		public int getLength() {
			return n;
		}

		public boolean isFixed() {
			return isFixed;
		}

		public boolean equals(Attribute other) {
			return (isCaseSensitive == other.isCaseSensitive);
		}

	}

	public static Attribute ATTR(int i) {
		return ATTR(i, false);
	}

	public static Attribute ATTR(int i, boolean isCase) {
		return ATTR(i, isCase, true);
	}

	public static Attribute ATTR(int i, boolean isCase, boolean isFixed) {
		if (i > 32000) {
			i = 32000;
		}
		return Attribute.getInstance(i, isCase, isFixed);
	}

	public CHAR(String s, boolean isCase) {
		this(s, s.length(), isCase, true);
	}

	public CHAR(String s, int length, boolean isCase, boolean isFixed) {
		if (s == null) {
			throw new IllegalArgumentException("expected String should not be null");
		}
		if (length > 32000) {
			length = 32000;
		}
		if (s.length() > length) {
			s = s.substring(0, length);
		} else if (isFixed) {
			int c = length - s.length();
			char[] pad = new char[c];
			for (int i = 0; i < c; i++) {
				pad[i] = ' ';
			}
			String spad = new String(pad);
			s = s + spad;
		}
		this.string = s;
		this.attr = Attribute.getInstance(length, isCase, isFixed);

	}

	public CHAR(String s) {
		this(s, s.length(), false, true);
	}

	public Attribute getAttribute() {
		return this.attr;
	}

	public char[] getChars() {
		return this.string.toCharArray();
	}

	public String getString() {
		return this.string;
	}

	@Override
	public int hashCode() {
		return string.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CHAR) {
			CHAR another = (CHAR) obj;
			if (string.equals(another.string)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.string;
	}

	public int compareTo(CHAR other) {
		boolean isCaseSensitive = getAttribute().isCaseSensitive();
		if (isCaseSensitive) {
			return this.getString().compareTo(other.getString());
		} else {
			return this.getString().toLowerCase().compareTo(other.getString().toLowerCase());
		}
	}

	public int getLength() {
		return getChars().length;
	}

}
