package com.ebay.nest.arithmetic;

public class BooleanUtil {

	public static Boolean and(Object o1, Object o2) {

		if (o1 == null || o2 == null) {
			return false;
		}

		if (o1 instanceof Boolean && o2 instanceof Boolean) {
			Boolean b1 = (Boolean) o1;
			boolean b2 = (Boolean) o2;
			return b1 && b2;
		}

		return false;
	}

	public static Boolean or(Object o1, Object o2) {

		if (o1 == null || o2 == null) {
			return false;
		}

		if (o1 instanceof Boolean && o2 instanceof Boolean) {
			Boolean b1 = (Boolean) o1;
			boolean b2 = (Boolean) o2;
			return b1 || b2;
		}

		return false;
	}

}
