package com.ebay.nest;

import java.util.ArrayList;
import java.util.List;

import com.ebay.nest.metastore.MetaException;
import com.ebay.nest.metastore.TableMetadata;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.SMALLINT;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class SchemaUtil {

	public static TableSchema_remove getTableSchema(TableMetadata meta) throws MetaException {
		String[] names = meta.getFieldNames();
		String[] types = meta.getFieldTypes();
		TableSchema_remove schema = new TableSchema_remove();

		for (int i = 0; i < names.length; i++) {

			String name = names[i];
			String type = types[i].toLowerCase();
			TypeInfo attr;
			if (type.startsWith("byteint")) {
				attr = BYTEINT.ATTR();
			} else if (type.startsWith("smallint")) {
				attr = SMALLINT.ATTR();
			} else if (type.startsWith("integer")) {
				attr = INTEGER.ATTR();
			} else if (type.startsWith("bigint")) {
				attr = BIGINT.ATTR();
			} else if (type.startsWith("decimal")) {
				int[] params = getParamsFromType(type);
				int pre = 0;
				int scale = 0;
				if (params.length == 0) {
					pre = 5;
					scale = 0;
				} else if (params.length == 1) {
					pre = params[0];
					scale = 0;
				} else if (params.length == 2) {
					pre = params[0];
					scale = params[1];
				}
				attr = DECIMAL.ATTR(pre, scale);

			} else if (type.startsWith("float")) {
				attr = FLOAT.ATTR();

			} else if (type.startsWith("varchar")) {
				int[] param = getParamsFromType(type);
				boolean isCase = meta.isCaseSensitive(name);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				attr = CHAR.ATTR(length, isCase, false);

			} else if (type.startsWith("char")) {
				int[] param = getParamsFromType(type);
				boolean isCase = meta.isCaseSensitive(name);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				attr = CHAR.ATTR(length, isCase, true);

			} else if (type.startsWith("varbyte")) {
				int[] param = getParamsFromType(type);
				int length = 64000;
				if (param.length == 1) {
					length = param[0];
				}
				attr = BYTE.ATTR(length, false);
			} else if (type.startsWith("byte")) {
				int[] param = getParamsFromType(type);
				int length = 1;
				if (param.length == 1) {
					length = param[0];
				}
				attr = BYTE.ATTR(length);
			} else if (type.startsWith("timestamp")) {
				String format = meta.getFieldFormat(name);
				attr = TIMESTAMP.ATTR(format);
			} else if (type.startsWith("time")) {
				String format = meta.getFieldFormat(name);
				attr = TIME.ATTR(format);
			} else if (type.startsWith("date")) {
				String format = meta.getFieldFormat(name);
				attr = DATE.ATTR(format);
			} else if (type.startsWith("interval")) {
				attr = null;
			} else if (type.startsWith("period")) {
				attr = null;
			} else {
				attr = null;
			}

			Column sc = new Column(name, attr);
			schema.addToCols(sc);
		}

		return schema;

	}

	private static int[] getParamsFromType(String s) {
		List<Integer> list = new ArrayList<Integer>();
		if (s.indexOf("(") != -1) {
			s = s.substring(s.indexOf("(") + 1);
			if (s.indexOf(')') != -1) {
				try {
					s = s.substring(0, s.indexOf(')'));
					String[] sp = s.split(",");
					for (String l : sp) {
						l = l.trim().toLowerCase();
						if (l.endsWith("k")) {
							l = l.substring(0, l.length() - 1);
							Integer value = Integer.valueOf(l);
							value = value * 1024;
							list.add(value);
						} else if (l.endsWith("m")) {
							l = l.substring(0, l.length() - 1);
							Integer value = Integer.valueOf(l);
							value = value * 1024 * 1024;
							list.add(value);
						} else {
							Integer value = Integer.valueOf(l);
							list.add(value);
						}

					}
				} catch (NumberFormatException e) {

				}
			}
		}
		return toIntArray(list.toArray(new Integer[list.size()]));
	}

	private static int[] toIntArray(Integer[] array) {
		if (array == null) {
			return null;

		} else if (array.length == 0) {
			return new int[] {};
		}
		final int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].intValue();

		}
		return result;

	}

}
