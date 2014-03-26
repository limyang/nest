package com.ebay.nest.analyzer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.Table;

public class QueryMetaData {

	public static final int DEST_INVALID = 0;
	public static final int DEST_TABLE = 1;
	public static final int DEST_PARTITION = 2;
	public static final int DEST_DFS_FILE = 3;
	public static final int DEST_REDUCE = 4;
	public static final int DEST_LOCAL_FILE = 5;

	private final HashMap<String, Table> aliasToTable;
	private final HashMap<String, Table> nameToDestTable;
	private final HashMap<String, String> nameToDestFile;
	private final HashMap<String, Integer> nameToDestType;
	private final HashMap<String, Map<String, String>> aliasToPartSpec;

	private static final Log LOG = LogFactory.getLog(QueryMetaData.class.getName());

	public QueryMetaData() {
		aliasToTable = new HashMap<String, Table>();
		nameToDestTable = new HashMap<String, Table>();

		nameToDestFile = new HashMap<String, String>();
		nameToDestType = new HashMap<String, Integer>();
		aliasToPartSpec = new HashMap<String, Map<String, String>>();

	}

	public HashMap<String, Table> getAliasToTable() {
		return aliasToTable;
	}

	public Table getTableForAlias(String alias) {
		return aliasToTable.get(alias.toLowerCase());
	}

	public void setSrcForAlias(String alias, Table tab) {
		aliasToTable.put(alias, tab);
	}

	public void setDestForAlias(String alias, Table tab) {
		nameToDestType.put(alias, Integer.valueOf(DEST_TABLE));
		nameToDestTable.put(alias, tab);
	}

	public void setDestForAlias(String alias, String fname, boolean isDfsFile) {
		nameToDestType.put(alias, isDfsFile ? Integer.valueOf(DEST_DFS_FILE) : Integer.valueOf(DEST_LOCAL_FILE));
		nameToDestFile.put(alias, fname);
	}

	public Integer getDestTypeForAlias(String alias) {
		return nameToDestType.get(alias.toLowerCase());
	}

	public Table getDestTableForAlias(String alias) {
		return nameToDestTable.get(alias.toLowerCase());
	}

	public String getDestFileForAlias(String alias) {
		return nameToDestFile.get(alias.toLowerCase());
	}

	public Table getSrcForAlias(String alias) {
		return aliasToTable.get(alias.toLowerCase());
	}

	public Map<String, String> getPartSpecForAlias(String alias) {
		return aliasToPartSpec.get(alias);
	}

	public void setPartSpecForAlias(String alias, Map<String, String> partSpec) {
		aliasToPartSpec.put(alias, partSpec);

	}

}
