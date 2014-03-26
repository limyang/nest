package com.ebay.nest.cascading.operation;

import java.util.ArrayList;
import java.util.List;

import cascading.flow.FlowProcess;
import cascading.operation.Aggregator;
import cascading.operation.AggregatorCall;
import cascading.operation.BaseOperation;
import cascading.operation.OperationCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

public class CoGroupDebug extends BaseOperation<CoGroupDebug.Context> implements Aggregator<CoGroupDebug.Context> {

	private static final long serialVersionUID = 1L;
	private Fields fields;

	public class Context {
		private List<TupleEntry> rights;

		public Context reset() {
			rights = new ArrayList<TupleEntry>();
			return this;
		}

	}

	public CoGroupDebug(Fields fieldDeclaration) {
		super(ANY, fieldDeclaration);
		fields = fieldDeclaration;
	}

	public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
		operationCall.setContext(new Context());
	}

	public void start(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		aggregatorCall.getContext().reset();

	}

	public void aggregate(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		TupleEntry input = aggregatorCall.getArguments();
		Tuple rs = new Tuple();
		for (int i = 0; i < fields.size(); i++) {
			Object o = input.getObject(fields.get(i));
			rs.add(o);
		}
		context.rights.add(new TupleEntry(rs));
	}

	public void complete(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		for (int k = 0; k < context.rights.size(); k++) {

			aggregatorCall.getOutputCollector().add(context.rights.get(k).getTuple());

		}
	}

}
