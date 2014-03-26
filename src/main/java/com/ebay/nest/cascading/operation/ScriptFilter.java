package com.ebay.nest.cascading.operation;

import cascading.flow.FlowProcess;
import cascading.operation.Filter;
import cascading.operation.FilterCall;

public class ScriptFilter extends ScriptOperation implements Filter<ScriptOperation.Context> {

	private static final long serialVersionUID = 4055294950181838055L;

	public ScriptFilter(String script) {
		super(ANY, script, Boolean.class);
	}

	public ScriptFilter(String script, String parameterName, Class parameterType) {
		super(1, script, Boolean.class, new String[] { parameterName }, new Class[] { parameterType });
	}

	public ScriptFilter(String script, Class[] expectedTypes) {
		super(expectedTypes.length, script, Boolean.class, expectedTypes);
	}

	public ScriptFilter(String script, String[] parameterNames, Class[] parameterTypes) {
		super(parameterTypes.length, script, Boolean.class, parameterNames, parameterTypes);
	}

	public String getScript() {
		return getBlock();
	}

	public boolean isRemove(FlowProcess flowProcess, FilterCall<Context> filterCall) {
		return (Boolean) evaluate(filterCall.getContext(), filterCall.getArguments());
	}
}