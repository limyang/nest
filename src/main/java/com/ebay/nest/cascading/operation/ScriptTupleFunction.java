package com.ebay.nest.cascading.operation;

import cascading.flow.FlowProcess;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;

public class ScriptTupleFunction extends ScriptOperation implements Function<ScriptOperation.Context> {

	private static final long serialVersionUID = 191656117239232260L;

	public ScriptTupleFunction(Fields fieldDeclaration, String script) {
		super(ANY, fieldDeclaration, script, Tuple.class);
	}

	public ScriptTupleFunction(Fields fieldDeclaration, String script, Class[] expectedTypes) {
		super(expectedTypes.length, fieldDeclaration, script, Tuple.class, expectedTypes);
	}

	public ScriptTupleFunction(Fields fieldDeclaration, String script, String[] parameterNames, Class[] parameterTypes) {
		super(parameterTypes.length, fieldDeclaration, script, Tuple.class, parameterNames, parameterTypes);
	}

	public String getScript() {
		return getBlock();
	}

	@Override
	public void operate(FlowProcess flowProcess, FunctionCall<Context> functionCall) {
		functionCall.getOutputCollector().add((Tuple) evaluate(functionCall.getContext(), functionCall.getArguments()));
	}
}
