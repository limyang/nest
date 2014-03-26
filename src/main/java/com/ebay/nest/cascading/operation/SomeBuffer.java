package com.ebay.nest.cascading.operation;

import java.util.Iterator;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Buffer;
import cascading.operation.BufferCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

public class SomeBuffer extends BaseOperation implements Buffer {

	public SomeBuffer(Fields f) {
		super(f);
	}

	public void operate(FlowProcess flowProcess, BufferCall bufferCall) {
		// get the group values for the current grouping
		TupleEntry group = bufferCall.getGroup();

		// get all the current argument values for this grouping
		Iterator<TupleEntry> arguments = bufferCall.getArgumentsIterator();

		// create a Tuple to hold our result values
		Tuple result = new Tuple();

		while (arguments.hasNext()) {
			TupleEntry argument = arguments.next();
			if (argument.get(0).toString().equals("130860000123")) {
				System.out.println("130860000123");
			}
//			bufferCall.getOutputCollector().add(argument);
			// insert some values into the result Tuple based on the arguemnts
		}

		// return the result Tuple
		// bufferCall.getOutputCollector().add(result);
	}
}