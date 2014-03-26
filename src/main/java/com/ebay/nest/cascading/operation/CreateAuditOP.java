package com.ebay.nest.cascading.operation;

import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

public class CreateAuditOP extends AuditOperation {

	private static final long serialVersionUID = 6245851206150579291L;

	public CreateAuditOP(Fields inputFields) {
		super(inputFields);
	}

	@Override
	protected TupleEntry process(TupleEntry entry) {
		markCreate();
		return entry;
	}
}
