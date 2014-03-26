package com.ebay.nest.cascading.operation;

import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.utils.FieldsUtil;

public class CastToStringOP extends AuditOperation {

	private static final long serialVersionUID = 1L;
	private boolean caseSensitive = false;

	public CastToStringOP(Fields inputFields) {
		super(inputFields);
	}

	public CastToStringOP(Fields inputFields, boolean caseSensitive) {
		super(inputFields);
		this.caseSensitive = caseSensitive;
	}

	@Override
	protected TupleEntry process(TupleEntry entry) {
		String[] names = FieldsUtil.names(inputFields);
		for (String name : names) {
			Object obj = entry.getObject(name);
			if (obj == null) {
				entry.setObject(name, "");
				continue;
			}
			String output = obj.toString();
			CHAR str = new CHAR(output, caseSensitive);
			entry.setObject(name, str);
		}
		return entry;
	}

}
