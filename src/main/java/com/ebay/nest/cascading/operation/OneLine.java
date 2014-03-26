package com.ebay.nest.cascading.operation;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

public class OneLine extends BaseOperation implements Function {

	private static final long serialVersionUID = 473240249974042171L;

	private String delimeter;

	public OneLine(String lineName, String delimeter) {
		super(new Fields(lineName));
		this.delimeter = delimeter;
	}

	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		TupleEntry entry = functionCall.getArguments();
		String result = entry.getTuple().toString(delimeter);
		Tuple tuple = new Tuple();
		tuple.add(result);
		functionCall.getOutputCollector().add(tuple);
	}
}
