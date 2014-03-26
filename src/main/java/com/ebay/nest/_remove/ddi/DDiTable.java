package com.ebay.nest._remove.ddi;

public class DDiTable {
	private String db;
	private String tb;
	private DDiTableType ddiTableType;
	private DDiUowType ddiUowType;

	public DDiTable(String db, String tb, DDiTableType ddiTableType,
			DDiUowType ddiUowType) {
		this.db = db;
		this.tb = tb;
		this.ddiTableType = ddiTableType;
		this.ddiUowType = ddiUowType;
	}

	public String tagName() {
		return String.format("%s[%s:%s]", db + "." + tb, ddiTableType
				.toString().toLowerCase(), ddiUowType.toString().toLowerCase());
	}

	public boolean isUowFrom() {
		return ddiUowType == DDiUowType.UOW_FROM;
	}

	public boolean isUowTo() {
		return ddiUowType == DDiUowType.UOW_TO;
	}

	public boolean isSnapshot() {
		return ddiTableType == DDiTableType.SNAPSHOT;
	}

	public boolean isStaging() {
		return ddiTableType == DDiTableType.STAGING;
	}

	public String getDB() {
		return db;
	}

	public String getTB() {
		return tb;
	}

}
