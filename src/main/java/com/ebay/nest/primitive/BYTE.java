package com.ebay.nest.primitive;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import com.ebay.nest.Data;

public final class BYTE implements Comparable<BYTE>, Data {

	private static final long serialVersionUID = 1L;

	private final byte[] bytes;

	private final Attribute attr;

	public final static class Attribute implements com.ebay.nest.TypeInfo {

		private static final long serialVersionUID = 1L;
		private static ConcurrentHashMap<String, Attribute> map = new ConcurrentHashMap<String, Attribute>();
		private int length;
		private boolean isFixed;

		private Attribute(int n, boolean isFixed) {
			this.length = n;
			this.isFixed = isFixed;
		}

		public static Attribute getInstance(int length, boolean isFixed) {
			String key = "" + length + ":" + isFixed;
			Attribute value = map.get(key);
			if (value == null) {
				value = new Attribute(length, isFixed);
				map.putIfAbsent(key, value);
				return map.get(key);// do not change to value!!
			}
			return value;
		}

		public String getFormat() {
			return null;
		}

		public Class getJavaClass() {
			return Byte.class;
		}

		public boolean isNullable() {
			return false;
		}

		public int getLength() {
			return this.length;
		}

		public boolean isFixed() {
			return isFixed;
		}

	}

	public static Attribute ATTR(int length) {
		return ATTR(length, true);
	}

	public static Attribute ATTR(int length, boolean isFixed) {
		if (length > 64000) {
			length = 64000;
		}
		return Attribute.getInstance(length, isFixed);
	}

	public BYTE(byte[] bytes) {
		this(bytes, bytes.length);
	}

	public BYTE(byte[] bytes, int length) {
		this(bytes, length, true);
	}

	public BYTE(byte[] bytes, int length, boolean isFixed) {
		if (bytes == null) {
			throw new IllegalArgumentException("expected byte array should not be null");
		}
		if (bytes.length > length) {
			length = bytes.length;
		}
		this.bytes = Arrays.copyOf(bytes, length);
		this.attr = Attribute.getInstance(length, isFixed);
	}

	public Attribute getAttribute() {
		return attr;
	}

	public byte[] getBytes() {
		return Arrays.copyOf(bytes, bytes.length);

	}

	@Override
	public int hashCode() {
		return bytes.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BYTE) {
			byte[] a = bytes;
			byte[] b = ((BYTE) obj).bytes;
			int m = a.length;
			int n = b.length;
			if (m != n) {
				return false;
			}
			int k = m;
			for (int i = 0; i < k; i++) {
				byte ai = a[i];
				byte bi = b[i];
				if (ai != bi) {
					return false;
				} else {
					continue;
				}
			}
			return true;

		}
		return false;
	}

	public int compareTo(BYTE other) {
		byte[] a = this.getBytes();
		byte[] b = other.getBytes();
		int m = a.length;
		int n = b.length;
		int k = (m > n) ? n : m;
		for (int i = 0; i < k; i++) {
			byte ai = a[i];
			byte bi = b[i];
			if (ai - bi > 0) {
				return 1;
			} else if (ai - bi < 0) {
				return -1;
			} else {
				continue;
			}
		}
		if (m > n) {
			return 1;
		} else if (m < n) {
			return -1;
		} else {
			return 0;
		}
	}

	public int getLength() {
		return getBytes().length;
	}

	@Override
	public String toString() {
		return new String(this.bytes);
	}

}
