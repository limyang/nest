package com.ebay.nest;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Table implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final private Log LOG = LogFactory.getLog("com.ebay.nest.Table");

	private com.ebay.nest.metastore.Table tTable;

	private List<Column> cols;

	private final TableType tabType;

	public Table(TableType tabType) {
		this.tabType = tabType;
	}

	public Table(com.ebay.nest.metastore.Table tTable) {
		this.tTable = tTable;
		tabType = TableType.TABLE;
	}

	public Table(List<Column> cols) {
		this.cols = cols;
		this.tabType = TableType.VIEW;
	}
	
	public Table(List<Column> cols, TableType tabType) {
		this.cols = cols;
		this.tabType = tabType;
	}
	
	public List<Column> getAllColumns() {
		if (isTable()  && tTable.isSetSd()){
			return tTable.getSd().getCols();
		}

		return this.cols;
	}

	public boolean isTable() {
		return TableType.TABLE.equals(getTableType());
	}

	public TableType getTableType() {
		return tabType;
	}

}
