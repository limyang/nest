package com.ebay.nest.metastore;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TableMetadata implements Serializable {

	private static final long serialVersionUID = 1118315244876344828L;
	private final int size;
	private final Map<String, Integer> posMap = new HashMap();
	private final String[] names;
	private final String[] types;
	private final boolean[] nullables;
	private final boolean[] caseSensitives;
	private final String[] formats;
	private final String[] defaults;

	public TableMetadata(String[] names, String[] types, boolean[] nullables, boolean[] caseSensitives, String[] formats, String[] defaults) {
		this.size = names.length;
		if ((types.length != this.size) || (nullables.length != this.size) || (caseSensitives.length != this.size)
				|| (formats.length != this.size)) {
			throw new IllegalArgumentException("Expected Auguments length is the same size ");
		}

		for (int i = 0; i < this.size; ++i) {
			this.posMap.put(names[i], Integer.valueOf(i));
		}
		this.names = ((String[]) Arrays.copyOf(names, this.size));

		this.types = ((String[]) Arrays.copyOf(types, this.size));
		this.nullables = Arrays.copyOf(nullables, this.size);
		this.caseSensitives = Arrays.copyOf(caseSensitives, this.size);
		this.formats = ((String[]) Arrays.copyOf(formats, this.size));
		this.defaults= Arrays.copyOf(defaults, this.size);
	}

	public String[] getFieldNames() {
		return ((String[]) Arrays.copyOf(this.names, this.names.length));
	}

	public String[] getFieldTypes() {
		return ((String[]) Arrays.copyOf(this.types, this.names.length));
	}

	public String getFieldType(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		int pos = getFieldPosition(name);
		return this.types[pos];
	}

	public String getFieldFormat(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		int pos = getFieldPosition(name);
		return this.formats[pos];
	}
	
	public String getFieldDefault(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		int pos = getFieldPosition(name);
		return this.defaults[pos];
	}

	public boolean isNullable(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		int pos = getFieldPosition(name);
		return this.nullables[pos];
	}

	public boolean isCaseSensitive(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		int pos = getFieldPosition(name);
		return this.caseSensitives[pos];
	}

	public int getFieldPosition(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Field name cannot be null.");
		}
		name = name.toLowerCase();
		return ((Integer) this.posMap.get(name)).intValue();
	}
}