package com.ebay.nest.cascading.operation;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Filter;
import cascading.operation.FilterCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

public class DeleteOP extends BaseOperation implements Filter {


	private static final long serialVersionUID = -3465395338713869181L;
	private Fields nullFields;

	public DeleteOP(Fields nullFields) {
		this.nullFields = nullFields;
	}

	public boolean isRemove(FlowProcess flowProcess, FilterCall filterCall) {
		TupleEntry tupleEntry = filterCall.getArguments();
		for (int i = 0; i < nullFields.size(); i++) {
			String name = (String) nullFields.get(i);
			Object o = tupleEntry.getObject(name);
			if (o != null) {
				return true;
			}
		}
		return false;
	}

}
