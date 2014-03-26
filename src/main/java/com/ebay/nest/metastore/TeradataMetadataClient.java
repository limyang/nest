package com.ebay.nest.metastore;

import in.masr.db.teradata.TeradataDB;
import in.masr.utils.ArrayU;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebay.nest.NestContext;

public class TeradataMetadataClient implements IMetadataClient {
	private Map<String, TableMetadata> metaMap = new HashMap();
	private Connection connection;
	private NestContext ctx;
	private static final String[] NO_LENGTH = { "bigint", "byteint", "smallint", "integer", "date", "time", "timestamp" };

	public TeradataMetadataClient(NestContext ctx) {
		this.ctx = ctx;
	}

	public TeradataMetadataClient() {
	}

	public void connect() throws MetaException {
		TeradataDB teradataDB = null;
		try {
			
			String user = ctx.getProperty("teradata_user");
			String passwd = ctx.getProperty("teradata_passwd");
			System.err.println("host:"+ctx.getProperty("teradata_db") + ":user:"+user+":passwd:"+passwd);
			teradataDB = new TeradataDB(ctx.getProperty("teradata_db"), user, passwd);
			// teradataDB = new TeradataDB("mozart.vip.ebay.com", "limyang",
			// "");
		} catch (ClassNotFoundException ex) {
			throw new MetaException(ex.getMessage());
		}
		try {
			this.connection = teradataDB.createConnection();
		} catch (SQLException e) {
			throw new MetaException(e.getMessage());
		}
	}

	public void reconnect() throws MetaException {
		try {
			if ((this.connection != null) && (!(this.connection.isClosed()))) {
				this.connection.close();
			}
			connect();
		} catch (SQLException e) {
			throw new MetaException(e.getMessage());
		}
	}

	public void close() {
		try {
			if ((this.connection != null) && (!(this.connection.isClosed())))
				this.connection.close();
		} catch (SQLException e) {
		}
	}

	public TableMetadata getMetadata(String database, String table) throws MetaException {
		try {
			if ((database == null) || (table == null)) {
				throw new IllegalArgumentException("Cannot get metadata: " + database + "." + "table");
			}

			database = database.toLowerCase();
			table = table.toLowerCase();
			if (this.metaMap.containsKey(database + "." + table)) {
				return ((TableMetadata) this.metaMap.get(database + "." + table));
			}
			if ((this.connection == null) || (this.connection.isClosed())) {
				throw new MetaException("Conections has been closed or not connected yet.");
			}

			String sql = "help table  " + database + "." + table;

			Statement stmt = this.connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			List<String> names = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			List<Boolean> nullables = new ArrayList<Boolean>();
			List<Boolean> caseSensitives = new ArrayList<Boolean>();

			List<String> formats = new ArrayList<String>();
			List<String> defaultVals = new ArrayList<String>();

			while (result.next()) {
				String name = result.getString(1).toLowerCase().trim();
				String typeStr = result.getString(2).trim();
				String nullableStr = result.getString(4).trim();
				String format = result.getString(5).trim();
				String maxLengthStr = result.getString(7).trim();
				String maxDecimalLengthStr = result.getString(8);
				if (maxDecimalLengthStr != null) {
					maxDecimalLengthStr = maxDecimalLengthStr.trim();
				}
				String maxDecimalRadixLengthStr = result.getString(9);
				if (maxDecimalRadixLengthStr != null) {
					maxDecimalRadixLengthStr = maxDecimalRadixLengthStr.trim();
				}
				String upperCaseStr = result.getString(12).trim();
				String defaultValue = result.getString(14);
				if (defaultValue != null) {
					defaultValue = defaultValue.trim();
				}

				int length1 = -1;
				int length2 = -1;
				names.add(name);

				String type = typeMapping(typeStr);
				if (type == null) {
					throw new MetaException(typeStr + " is not a recognized.");
				}
				if (type.equals("decimal")) {
					length1 = Integer.parseInt(maxDecimalLengthStr);
					length2 = Integer.parseInt(maxDecimalRadixLengthStr);
					type = "decimal(" + length1 + "," + length2 + ")";
				} else if (!(ArrayU.contains(NO_LENGTH, type))) {
					length1 = Integer.parseInt(maxLengthStr);
					type = type + "(" + length1 + ")";
				}
				types.add(type);

				boolean nullable = nullableStr.equals("Y");
				nullables.add(Boolean.valueOf(nullable));
				boolean caseSensitive = upperCaseStr.equals("Y");
				caseSensitives.add(Boolean.valueOf(caseSensitive));
				formats.add(format);
				defaultVals.add(defaultValue);
			}

			boolean[] nullableArr = ArrayU.toPrimitive((Boolean[]) nullables.toArray(new Boolean[0]));

			boolean[] caseSensitiveArr = ArrayU.toPrimitive((Boolean[]) caseSensitives.toArray(new Boolean[0]));

			TableMetadata tableMetadata = new TableMetadata((String[]) names.toArray(new String[0]),
					(String[]) types.toArray(new String[0]), nullableArr, caseSensitiveArr,
					(String[]) formats.toArray(new String[0]), defaultVals.toArray(new String[defaultVals.size()]));

			this.metaMap.put(database + "." + table, tableMetadata);
			return tableMetadata;
		} catch (SQLException e) {
			throw new MetaException(e.getMessage());
		}
	}

	private String typeMapping(String type) {
		if (type.equals("D")) {
			return "decimal";
		}
		if (type.equals("DA")) {
			return "date";
		}
		if (type.equals("I1")) {
			return "byteint";
		}
		if (type.equals("CF")) {
			return "char";
		}
		if (type.equals("I")) {
			return "integer";
		}
		if (type.equals("TS")) {
			return "timestamp";
		}
		if (type.equals("CV")) {
			return "varchar";
		}

		if (type.equals("I2")) {
			return "smallint";
		}

		if (type.equals("AT")) {
			return "time";
		}
		if (type.equals("BF")) {
			return "byte";
		}

		if (type.equals("I8")) {
			return "bigint";
		}
		return null;
	}
}