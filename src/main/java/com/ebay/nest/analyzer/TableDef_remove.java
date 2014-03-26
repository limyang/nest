package com.ebay.nest.analyzer;


public final class TableDef_remove {

	private String name;

	private String db;

	private String tb;

	private String path;

	private TapFormatType type;

	public TableDef_remove(String name, String path, TapFormatType type) {

		this.name = name;
		this.path = path;
		this.type = type;
	}

	public TableDef_remove(String name, String db, String tb, String path,
			TapFormatType type) {

		this(name, path, type);
		this.db = db;
		this.tb = tb;

	}

	public TapFormatType getTapType() {

		return type;
	}

	public String getName() {
		return name;
	}

	public String getTB() {
		return tb;
	}

	public String getDB() {
		return db;
	}

	public String getPath() {
		return path;
	}


}
