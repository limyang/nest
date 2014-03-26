package com.ebay.nest.metastore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ebay.nest.Column;

public class Table implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String tableName;
	private String dbName;
	private Storage sd;
	private List<Column> partitionKeys;
	private Map<String, String> parameters;
	private String tableType;

	public Table() {
	}

	public Table(String tableName, String dbName, Storage sd, List<Column> partitionKeys,
			Map<String, String> parameters, String tableType) {
		this();
		this.tableName = tableName;
		this.dbName = dbName;
		this.sd = sd;
		this.partitionKeys = partitionKeys;
		this.parameters = parameters;
		this.tableType = tableType;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void unsetTableName() {
		this.tableName = null;
	}

	public boolean isSetTableName() {
		return this.tableName != null;
	}

	public void setTableNameIsSet(boolean value) {
		if (!value) {
			this.tableName = null;
		}
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void unsetDbName() {
		this.dbName = null;
	}

	public boolean isSetDbName() {
		return this.dbName != null;
	}

	public void setDbNameIsSet(boolean value) {
		if (!value) {
			this.dbName = null;
		}
	}

	public Storage getSd() {
		return this.sd;
	}

	public void setSd(Storage sd) {
		this.sd = sd;
	}

	public void unsetSd() {
		this.sd = null;
	}

	public boolean isSetSd() {
		return this.sd != null;
	}

	public void setSdIsSet(boolean value) {
		if (!value) {
			this.sd = null;
		}
	}

	public int getPartitionKeysSize() {
		return (this.partitionKeys == null) ? 0 : this.partitionKeys.size();
	}

	public java.util.Iterator<Column> getPartitionKeysIterator() {
		return (this.partitionKeys == null) ? null : this.partitionKeys.iterator();
	}

	public void addToPartitionKeys(Column elem) {
		if (this.partitionKeys == null) {
			this.partitionKeys = new ArrayList<Column>();
		}
		this.partitionKeys.add(elem);
	}

	public List<Column> getPartitionKeys() {
		return this.partitionKeys;
	}

	public void setPartitionKeys(List<Column> partitionKeys) {
		this.partitionKeys = partitionKeys;
	}

	public void unsetPartitionKeys() {
		this.partitionKeys = null;
	}

	/** Returns true if field partitionKeys is set (has been assigned a value) and false otherwise */
	public boolean isSetPartitionKeys() {
		return this.partitionKeys != null;
	}

	public void setPartitionKeysIsSet(boolean value) {
		if (!value) {
			this.partitionKeys = null;
		}
	}

	public int getParametersSize() {
		return (this.parameters == null) ? 0 : this.parameters.size();
	}

	public void putToParameters(String key, String val) {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}
		this.parameters.put(key, val);
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public void unsetParameters() {
		this.parameters = null;
	}

	public boolean isSetParameters() {
		return this.parameters != null;
	}

	public void setParametersIsSet(boolean value) {
		if (!value) {
			this.parameters = null;
		}
	}

	public String getTableType() {
		return this.tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public void unsetTableType() {
		this.tableType = null;
	}

	public boolean isSetTableType() {
		return this.tableType != null;
	}

	public void setTableTypeIsSet(boolean value) {
		if (!value) {
			this.tableType = null;
		}
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();

		boolean present_tableName = true && (isSetTableName());
		builder.append(present_tableName);
		if (present_tableName)
			builder.append(tableName);

		boolean present_dbName = true && (isSetDbName());
		builder.append(present_dbName);
		if (present_dbName)
			builder.append(dbName);

		boolean present_sd = true && (isSetSd());
		builder.append(present_sd);
		if (present_sd)
			builder.append(sd);

		boolean present_partitionKeys = true && (isSetPartitionKeys());
		builder.append(present_partitionKeys);
		if (present_partitionKeys)
			builder.append(partitionKeys);

		boolean present_parameters = true && (isSetParameters());
		builder.append(present_parameters);
		if (present_parameters)
			builder.append(parameters);

		boolean present_tableType = true && (isSetTableType());
		builder.append(present_tableType);
		if (present_tableType)
			builder.append(tableType);

		return builder.toHashCode();
	}

	public int compareTo(Table other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;
		Table typedOther = (Table) other;

		lastComparison = Boolean.valueOf(isSetTableName()).compareTo(typedOther.isSetTableName());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetTableName()) {

		}
		lastComparison = Boolean.valueOf(isSetDbName()).compareTo(typedOther.isSetDbName());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetDbName()) {

		}

		lastComparison = Boolean.valueOf(isSetSd()).compareTo(typedOther.isSetSd());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetSd()) {

		}
		lastComparison = Boolean.valueOf(isSetPartitionKeys()).compareTo(typedOther.isSetPartitionKeys());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetPartitionKeys()) {

		}
		lastComparison = Boolean.valueOf(isSetParameters()).compareTo(typedOther.isSetParameters());
		if (lastComparison != 0) {
			return lastComparison;
		}

		lastComparison = Boolean.valueOf(isSetTableType()).compareTo(typedOther.isSetTableType());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetTableType()) {

		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Table(");
		boolean first = true;

		sb.append("tableName:");
		if (this.tableName == null) {
			sb.append("null");
		} else {
			sb.append(this.tableName);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("dbName:");
		if (this.dbName == null) {
			sb.append("null");
		} else {
			sb.append(this.dbName);
		}
		first = false;
		if (!first)
			sb.append(", ");
		first = false;
		if (!first)
			sb.append(", ");
		first = false;
		if (!first)
			sb.append(", ");
		first = false;
		if (!first)
			sb.append(", ");
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("sd:");
		if (this.sd == null) {
			sb.append("null");
		} else {
			sb.append(this.sd);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("partitionKeys:");
		if (this.partitionKeys == null) {
			sb.append("null");
		} else {
			sb.append(this.partitionKeys);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("parameters:");
		if (this.parameters == null) {
			sb.append("null");
		} else {
			sb.append(this.parameters);
		}
		first = false;
		if (!first)
			sb.append(", ");
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("tableType:");
		if (this.tableType == null) {
			sb.append("null");
		} else {
			sb.append(this.tableType);
		}
		first = false;

		sb.append(")");
		return sb.toString();
	}

}
