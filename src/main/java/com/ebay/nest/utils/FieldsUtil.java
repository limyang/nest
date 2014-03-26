package com.ebay.nest.utils;

import java.util.Set;
import java.util.TreeSet;

import cascading.tuple.Fields;

public class FieldsUtil {

	public static Fields pure(Fields fields) {
		return new Fields(FieldsUtil.names(fields));
	}

	public static Fields create(String... names) {

		for (int i = 0; i < names.length; i++) {
			names[i] = names[i].toLowerCase();
		}
		return new Fields(names);
	}

	/**
	 * @param s
	 *            array of String
	 * @return Fields instance
	 */
	public static Fields fields(String... s) {
		return new Fields(s);
	}

	/**
	 * Get new Fields which being prefixed with the specified string.
	 * 
	 * @param prefix
	 * @param fields
	 *            array of String
	 * @return Fields instance
	 */
	public static Fields prefix(Fields fields, String prefix) {
		Fields newFields = new Fields();
		for (int i = 0; i < fields.size(); i++) {
			String name = (String) fields.get(i);
			name = prefix + name;
			Class type = fields.getTypeClass(i);
			newFields = append(newFields, name, type);
		}
		return newFields;
	}

	public static Fields getExtraFields(Fields fields, Fields fieldsPlusExtra) {
		Fields extra = new Fields();
		String[] targetFieldNames = FieldsUtil.names(fieldsPlusExtra);
		for (String fieldName : targetFieldNames) {
			if (!fields.contains(new Fields(fieldName))) {
				extra = extra.append(new Fields(fieldName));
			}
		}
		return extra;
	}

	public static String[] names(Fields fields) {
		String[] names = new String[fields.size()];

		for (int i = 0; i < names.length; i++) {
			Comparable comparable = fields.get(i);
			if (comparable instanceof String)
				names[i] = (String) comparable;
			else
				names[i] = "$" + comparable;
		}

		return names;
	}

	public static String[] names(Fields fields, int pos[]) {
		return ArrayUtil.select(pos, names(fields));
	}

	public static Fields rename(Fields fields, int from, String[] names) {
		Fields newFields = new Fields();
		int subLength = names.length;
		for (int i = 0; i < fields.size(); i++) {
			String name;
			Class type;
			if (i < from || i >= from + subLength) {
				name = (String) fields.get(i);
				type = fields.getTypeClass(i);
			} else {
				name = names[i - from];
				type = fields.getTypeClass(i);
			}
			newFields = append(newFields, name, type);
		}
		return newFields;
	}

	public static Fields sub(Fields fields, int start, int length) {
		Fields newFields = new Fields();
		for (int i = start; i < start + length; i++) {
			String name = (String) fields.get(i);
			Class type = fields.getTypeClass(i);
			newFields = append(newFields, name, type);
		}
		return newFields;

	}

	public static Fields select(Fields fields, String[] names) {
		Fields newFields = new Fields();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			if (fields.contains(new Fields(name))) {
				Class type = fields.getTypeClass(name);
				newFields = append(newFields, name, type);
			}
		}
		return newFields;
	}

	public static Fields select(Fields fields, int pos[]) {
		Fields newFields = new Fields();
		for (int i = 0; i < pos.length; i++) {
			String name = (String) fields.get(i);
			Class type = fields.getTypeClass(i);
			newFields = append(newFields, name, type);
		}
		return newFields;
	}

	public static Fields append(Fields fields, String name, Class type) {
		Fields newFields = copy(fields);
		if (type != null) {
			newFields = newFields.append(new Fields(name, type));
		} else {
			newFields = newFields.append(new Fields(name));
		}
		return newFields;
	}

	public static Fields copy(Fields fields) {
		String[] names = names(fields);
		Class[] types = fields.getTypesClasses();
		return new Fields(names, types);
	}

	public static Fields merge(Fields... fields) {
		Fields newFields = Fields.NONE;
		Set<String> set = new TreeSet<String>();

		for (Fields field : fields) {
			if (field == null) {
				continue;
			}
			for (int i = 0; i < field.size(); i++) {
				String name = (String) field.get(i);
				if (set.contains(name)) {
					continue;
				}
				set.add(name);

				newFields = newFields.append(new Fields(field.get(i), field.getType(i)));
			}

		}
		return newFields;
	}

	public static Fields remove(Fields f, Set<String> removes) {
		Fields res = Fields.NONE;
		for (int i = 0; i < f.size(); i++) {
			String name = (String) f.get(i);
			if (removes.contains(name)) {
				continue;
			}
			res=res.append(new Fields(name, f.getType(i)));

		}
		return res;

	}

	public static boolean contain(Fields parent, Fields child) {
		if (child == null || parent == null) {
			return false;
		}
		for (int i = 0; i < child.size(); i++) {
			String fieldName = (String) child.get(i);
			boolean found = false;
			for (int j = 0; j < parent.size(); j++) {
				String parentFiledName = (String) parent.get(j);
				if (parentFiledName.endsWith(fieldName)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}

		return true;
	}

	public static String toString(Fields fields) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.size() - 1; i++) {
			sb.append(fields.get(i));
			sb.append(",");
		}
		sb.append(fields.get(fields.size() - 1));
		return sb.toString();

	}
}
