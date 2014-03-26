package com.ebay.nest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableSchema_remove implements Serializable {

	private static final long serialVersionUID = 989211721009516886L;

	private List<Column> cols;

	private Map<String, TypeInfo> coltypeMap = new HashMap<String, TypeInfo>();
	private String inputFormat;
	private String outputFormat;
	private String location;

	public TableSchema_remove() {
	}

	public int getColsSize() {
		return (this.cols == null) ? 0 : this.cols.size();
	}

	public java.util.Iterator<Column> getColsIterator() {
		return (this.cols == null) ? null : this.cols.iterator();
	}

	public void addToCols(Column elem) {
		if (this.cols == null) {
			this.cols = new ArrayList<Column>();
		}
		this.cols.add(elem);
		coltypeMap.put(elem.getName(), elem.getType());
	}

	public TypeInfo getAttributeForCol(String col) {
		return coltypeMap.get(col);
	}

	public List<Column> getCols() {
		return this.cols;
	}

	public void setCols(List<Column> cols) {
		this.cols = cols;
	}

	public void unsetCols() {
		this.cols = null;
	}

	public boolean isSetCols() {
		return this.cols != null;
	}

	public void setColsIsSet(boolean value) {
		if (!value) {
			this.cols = null;
		}
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void unsetLocation() {
		this.location = null;
	}

	public boolean isSetLocation() {
		return this.location != null;
	}

	public void setLocationIsSet(boolean value) {
		if (!value) {
			this.location = null;
		}
	}

	public String getInputFormat() {
		return this.inputFormat;
	}

	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}

	public void unsetInputFormat() {
		this.inputFormat = null;
	}

	public boolean isSetInputFormat() {
		return this.inputFormat != null;
	}

	public void setInputFormatIsSet(boolean value) {
		if (!value) {
			this.inputFormat = null;
		}
	}

	public String getOutputFormat() {
		return this.outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public void unsetOutputFormat() {
		this.outputFormat = null;
	}

	public boolean isSetOutputFormat() {
		return this.outputFormat != null;
	}

	public void setOutputFormatIsSet(boolean value) {
		if (!value) {
			this.outputFormat = null;
		}
	}

}
