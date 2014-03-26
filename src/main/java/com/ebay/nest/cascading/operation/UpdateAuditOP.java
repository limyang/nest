package com.ebay.nest.cascading.operation;

import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

public class UpdateAuditOP extends AuditOperation {

	private static final long serialVersionUID = 6316906150402013118L;

	protected UpdateAuditOP(Fields inputFields) {
		super(inputFields);
	}

	@Override
	protected TupleEntry process(TupleEntry entry) {
		markUpdate();
		return entry;
	}
}
