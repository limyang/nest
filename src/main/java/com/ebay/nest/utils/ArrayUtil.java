package com.ebay.nest.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayUtil extends in.masr.utils.ArrayU {
	public static int[] indexOf(Object[] arr, Object[] sub) {
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < sub.length; i++) {
			Object subObject = sub[i];
			int index = indexOf(arr, subObject);
			if (index == -1) {
				continue;
			}
			ret.add(index);
		}

		return toPrimitive(ret.toArray(new Integer[0]));
	}

	public static String join(int[] list, String delim) {
		return join(list, delim, false);
	}

	public static String join(int[] list, String delim, boolean printNull) {
		StringBuffer buffer = new StringBuffer();
		int count = 0;

		for (Object s : list) {
			if (count != 0)
				buffer.append(delim);

			if (printNull || s != null)
				buffer.append(s);

			count++;
		}

		return buffer.toString();
	}

	public static String join(String delim, String... strings) {
		return join(delim, false, strings);
	}

	public static String join(String delim, boolean printNull, String... strings) {
		return join(strings, delim, printNull);
	}

	/**
	 * This method joins the values in the given list with the delim String value.
	 * 
	 * @param list
	 * @param delim
	 * @return a String
	 */
	public static String join(Object[] list, String delim) {
		return join(list, delim, false);
	}

	public static String join(Object[] list, String delim, boolean printNull) {
		return join(list, delim, printNull, 0);
	}

	public static String join(Object[] list, String delim, boolean printNull, int beginAt) {
		return join(list, delim, printNull, beginAt, list.length - beginAt);
	}

	public static String join(Object[] list, String delim, boolean printNull, int beginAt, int length) {
		StringBuffer buffer = new StringBuffer();
		int count = 0;

		for (int i = beginAt; i < beginAt + length; i++) {
			Object s = list[i];
			if (count != 0)
				buffer.append(delim);

			if (printNull || s != null)
				buffer.append(s);

			count++;
		}

		return buffer.toString();
	}

	/**
	 * This method joins each value in the collection with a tab character as the delimiter.
	 * 
	 * @param collection
	 * @return a String
	 */
	public static String join(Collection collection) {
		return join(collection, "\t");
	}

	/**
	 * This method joins each valuein the collection with the given delimiter.
	 * 
	 * @param collection
	 * @param delim
	 * @return a String
	 */
	public static String join(Collection collection, String delim) {
		return join(collection, delim, false);
	}

	public static String join(Collection collection, String delim, boolean printNull) {
		StringBuffer buffer = new StringBuffer();

		join(buffer, collection, delim, printNull);

		return buffer.toString();
	}

	/**
	 * This method joins each value in the collection with the given delimiter. All results are appended to the given
	 * {@link StringBuffer} instance.
	 * 
	 * @param buffer
	 * @param collection
	 * @param delim
	 */
	public static void join(StringBuffer buffer, Collection collection, String delim) {
		join(buffer, collection, delim, false);
	}

	public static void join(StringBuffer buffer, Collection collection, String delim, boolean printNull) {
		int count = 0;

		for (Object s : collection) {
			if (count != 0)
				buffer.append(delim);

			if (printNull || s != null)
				buffer.append(s);

			count++;
		}
	}
}
