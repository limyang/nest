package com.ebay.nest.cascading.operation;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

public class FinalOutputOP extends BaseOperation implements Function {

	private static final long serialVersionUID = 5248109042865924098L;
	protected Fields inputFields;
	private String[] names;

	public FinalOutputOP(Fields inputFields, String[] names) {
		super(inputFields.size(), inputFields);
		this.inputFields = inputFields;
		this.names = names;
	}

	protected TupleEntry process(TupleEntry entry) {
		for (String name : names) {
			Object obj = entry.getObject(name);
			if (obj == null) {
				entry.setObject(name, "");
				continue;
			}
			String output = output(obj);
			entry.setObject(name, output);
		}
		return entry;
	}

	private String output(Object val) {

		return val.toString();
	}

	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		TupleEntry entry = functionCall.getArguments();
		entry = new TupleEntry(entry);
		entry = process(entry);
		functionCall.getOutputCollector().add(entry);

	}
}
