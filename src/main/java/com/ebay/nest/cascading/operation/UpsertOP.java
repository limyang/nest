package com.ebay.nest.cascading.operation;

import java.util.ArrayList;
import java.util.List;

import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.ebay.nest.utils.FieldsUtil;

public class UpsertOP extends AuditOperation {

	private static final long serialVersionUID = -750783182947268660L;

	private String INC_PREFIX = "INC-";

	private String[] joinFieldNames;

	public UpsertOP(Fields inputFields, String[] joinFieldNames) {
		super(inputFields);
		this.joinFieldNames = joinFieldNames;
	}

	public UpsertOP(String incPrefix, Fields inputFields, String[] joinFieldNames) {
		this(inputFields, joinFieldNames);
		this.INC_PREFIX = incPrefix;
	}

	@Override
	protected TupleEntry process(TupleEntry entry) {

		boolean baseHitJoin = true;
		for (String fieldName : joinFieldNames) {
			baseHitJoin = baseHitJoin && entry.getObject(fieldName) != null;
		}

		boolean incHitJoin = true;
		for (String fieldName : joinFieldNames) {
			String incJoinFieldName = INC_PREFIX + fieldName;
			incHitJoin = incHitJoin && entry.getObject(incJoinFieldName) != null;
		}

		if (!baseHitJoin && incHitJoin) {
			markCreate();
		}
		if (baseHitJoin && incHitJoin) {
			markUpdate();
		}

		String[] resultFieldNames = getBaseNames();

		if (incHitJoin || (!incHitJoin && !baseHitJoin && baseTotallyNull(entry) && !incTotallyNull(entry))) {
			// insert or update
			for (String fieldName : resultFieldNames) {
				if (inputFields.contains(new Fields(INC_PREFIX + fieldName))) {
					entry.setObject(fieldName, entry.getObject(INC_PREFIX + fieldName));
				}
			}
		}
		return entry;
	}

	private boolean incTotallyNull(TupleEntry entry) {
		boolean isAllNull = true;
		for (String fieldName : getBaseNames()) {
			if (inputFields.contains(new Fields(INC_PREFIX + fieldName))) {
				isAllNull = isAllNull && entry.getObject(INC_PREFIX + fieldName) == null;
			}
		}
		return isAllNull;
	}

	private boolean baseTotallyNull(TupleEntry entry) {
		boolean isAllNull = true;
		for (String fieldName : getBaseNames()) {
			isAllNull = isAllNull && entry.getObject(fieldName) == null;
		}
		return isAllNull;
	}

	private String[] getBaseNames() {
		List<String> list = new ArrayList<String>();
		for (String name : FieldsUtil.names(inputFields)) {
			if (!name.startsWith(INC_PREFIX)) {
				list.add(name);
			}
		}
		return list.toArray(new String[0]);
	}
}