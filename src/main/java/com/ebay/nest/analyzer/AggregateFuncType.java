package com.ebay.nest.analyzer;

public enum AggregateFuncType {
	AVG("AVG"), SUM("SUM"), MAX("MAX"), MIN("MIN"), COUNT("COUNT");
	private String name;

	AggregateFuncType(String s) {
		name = s;

	}

	public String toString() {
		return name;
	}

	public static  AggregateFuncType getType(String s) {

		for (AggregateFuncType value : AggregateFuncType.values()) {
			String name = value.toString();
			if (name.equalsIgnoreCase(s)) {
				return value;
			}
		}

		return null;
	}
}
