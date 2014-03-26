package com.ebay.nest.metastore;

import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.PERIOD;
import com.ebay.nest.primitive.SMALLINT;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;


import cascading.tuple.Fields;


public class MetadataUtil {

	public static TableMetadata getMetadata(String database, String table) throws MetaException {
		return MetadataFactory.getInstance().getMetadata(database, table);
	}

	public static Fields getFields(String database, String table) throws MetaException {
		TableMetadata meta = getMetadata(database, table);
		String[] names = meta.getFieldNames();
		Fields fields = Fields.NONE;
		for (int i = 0; i < names.length; i++) {

			fields = fields.append(new Fields(names[i], getFieldType(database, table, names[i])));
		}

		return fields;
	}

	public static String[] getFieldNames(String database, String table) throws MetaException {
		TableMetadata meta = getMetadata(database, table);
		String[] names = meta.getFieldNames();
		return names;
	}

	public static Class[] getFieldTypes(String database, String table) throws MetaException {
		TableMetadata meta = getMetadata(database, table);
		String[] dbTypes = meta.getFieldTypes();
		return getTypesByDBTypes(dbTypes);
	}

	public static Class getFieldType(String database, String table, String field) throws MetaException {
		TableMetadata meta = getMetadata(database, table);
		String dbType = meta.getFieldType(field);
		return getTypeByDBType(dbType);
	}

	private static Class[] getTypesByDBTypes(String[] types) {
		List<Class> javaTypes = new ArrayList<Class>();
		for (String type : types) {
			javaTypes.add(getTypeByDBType(type));
		}
		return javaTypes.toArray(new Class[0]);
	}

	private static Class getTypeByDBType(String type) {
		if (type.startsWith("byteint")) {
			return BYTEINT.class;
		} else if (type.startsWith("smallint")) {
			return SMALLINT.class;
		} else if (type.startsWith("integer")) {
			return INTEGER.class;
		} else if (type.startsWith("bigint")) {
			return BIGINT.class;
		} else if (type.startsWith("decimal")) {
			return DECIMAL.class;
		} else if (type.startsWith("float")) {
			return FLOAT.class;
		} else if (type.startsWith("varchar") || type.startsWith("char")) {
			return CHAR.class;
		} else if (type.startsWith("varbyte") || type.startsWith("byte")) {
			return BYTE.class;
		} else if (type.startsWith("timestamp")) {
			return TIMESTAMP.class;
		} else if (type.startsWith("time")) {
			return TIME.class;
		} else if (type.startsWith("date")) {
			return DATE.class;
		} else if (type.startsWith("interval")) {
			return INTERVAL.class;
		} else if (type.startsWith("period")) {
			return PERIOD.class;
		} else {
			return null;
		}
	}

}
