package com.ebay.nest._remove.ddi;

import in.masr.utils.ArrayU;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.lang.ArrayUtils;

import cascading.tuple.Fields;

import com.ebay.nest.metastore.MetaException;
import com.ebay.nest.metastore.MetadataUtil;

public class Audit {
	public static final String[] CREATE_DATE_COLUMNS = new String[] {
			"cre_date", "create_date", "cre_ts", "create_ts", "cre_dt" };

	public static final String[] UPDATE_DATE_COLUMNS = new String[] {
			"upd_date", "update_date", "upd_ts", "update_ts", "upd_dt" };

	public static final String[] CREATE_USER_COLUMNS = new String[] {
			"cre_user", "create_user" };

	public static final String[] UPDATE_USER_COLUMNS = new String[] {
			"upd_user", "update_user" };

	public static final String[] CREATE_COLUMNS = (String[]) ArrayU.addAll(
			CREATE_DATE_COLUMNS, CREATE_USER_COLUMNS);

	public static final String[] UPDATE_COLUMNS = (String[]) ArrayU.addAll(
			UPDATE_DATE_COLUMNS, UPDATE_USER_COLUMNS);

	public static String getCreateDateFieldName(String database, String table)
			throws IOException, SQLException, MetaException {
		return selectOneFromColumns(database, table, CREATE_DATE_COLUMNS);
	}

	public static String getUpdateDateFieldName(String database, String table)
			throws IOException, SQLException, MetaException {
		return selectOneFromColumns(database, table, UPDATE_DATE_COLUMNS);
	}

	public static String getCreateUserFieldName(String database, String table)
			throws IOException, SQLException, MetaException {
		return selectOneFromColumns(database, table, CREATE_USER_COLUMNS);
	}

	public static String getUpdateUserFieldName(String database, String table)
			throws IOException, SQLException, MetaException {
		return selectOneFromColumns(database, table, UPDATE_USER_COLUMNS);

	}

	public static boolean isAuditDateColumn(String name) {
		return ArrayUtils.contains(CREATE_DATE_COLUMNS, name)
				|| ArrayUtils.contains(UPDATE_DATE_COLUMNS, name);
	}

	public static boolean isAuditUserColumn(String name) {
		return ArrayUtils.contains(CREATE_USER_COLUMNS, name)
				|| ArrayUtils.contains(UPDATE_USER_COLUMNS, name);
	}

	public static boolean isAuditCreDateColumn(String name) {
		return ArrayUtils.contains(CREATE_DATE_COLUMNS, name);
	}

	public static boolean isAuditUpdDateColumn(String name) {
		return ArrayUtils.contains(UPDATE_DATE_COLUMNS, name);
	}

	public static boolean isAuditCreUserColumn(String name) {
		return ArrayUtils.contains(CREATE_USER_COLUMNS, name);
	}

	public static boolean isAuditUpdUserColumn(String name) {
		return ArrayUtils.contains(UPDATE_USER_COLUMNS, name);
	}

	private static String selectOneFromColumns(String database, String table,
			String[] columns) throws IOException, SQLException, MetaException {
		Fields fields = MetadataUtil.getFields(database, table);
		for (String column : columns) {
			if (fields.contains(new Fields(column))) {
				return column;
			}
		}
		return null;
	}

}
