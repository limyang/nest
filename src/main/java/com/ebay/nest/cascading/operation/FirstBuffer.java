package com.ebay.nest.cascading.operation;

import java.util.Iterator;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Buffer;
import cascading.operation.BufferCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

public class FirstBuffer extends BaseOperation implements Buffer {
	private final int firstN;

	public FirstBuffer() {
		super(Fields.ARGS);

		firstN = 1;
	}

	public FirstBuffer(int firstN) {
		super(Fields.ARGS);

		this.firstN = firstN;
	}

	public FirstBuffer(Fields fieldDeclaration) {
		super(fieldDeclaration.size(), fieldDeclaration);

		this.firstN = 1;
	}

	public FirstBuffer(Fields fieldDeclaration, int firstN) {
		super(fieldDeclaration.size(), fieldDeclaration);

		this.firstN = firstN;
	}

	public int getFirstN() {
		return firstN;
	}

	public void operate(FlowProcess flowProcess, BufferCall bufferCall) {
		Iterator<TupleEntry> iterator = bufferCall.getArgumentsIterator();

		int count = 0;

		while (count < firstN && iterator.hasNext()) {
			bufferCall.getOutputCollector().add(iterator.next());
			count++;
		}
	}
}
