package com.ebay.nest.cascading.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ScriptEvaluator;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.OperationCall;
import cascading.operation.OperationException;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import cascading.tuple.Tuples;
import cascading.tuple.coerce.Coercions;
import cascading.tuple.type.CoercibleType;
import cascading.tuple.util.TupleViews;
import cascading.util.Util;

import com.ebay.nest.metrics.MetricsTimer;
import com.ebay.nest.metrics.PerfLogger;
import com.ebay.nest.metrics.MetricsTimer.MetricsScope;

public abstract class ScriptOperation extends BaseOperation<ScriptOperation.Context> {

	private static final long serialVersionUID = 2560248462259959448L;

	private static final String CLASS_NAME = ScriptOperation.class.getName();

	private MetricsScope scope;

	protected final String block;

	protected Class[] parameterTypes;

	protected String[] parameterNames;

	protected Class returnType = Object.class;

	public ScriptOperation(int numArgs, Fields fieldDeclaration, String block) {
		super(numArgs, fieldDeclaration);
		this.block = block;
		this.returnType = fieldDeclaration.getTypeClass(0) == null ? this.returnType : fieldDeclaration.getTypeClass(0);
	}

	public ScriptOperation(int numArgs, Fields fieldDeclaration, String block, Class returnType) {
		super(numArgs, fieldDeclaration);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;
	}

	public ScriptOperation(int numArgs, Fields fieldDeclaration, String block, Class returnType, Class[] expectedTypes) {
		super(numArgs, fieldDeclaration);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;

		if (expectedTypes == null)
			throw new IllegalArgumentException("expectedTypes may not be null");

		this.parameterTypes = Arrays.copyOf(expectedTypes, expectedTypes.length);
	}

	public ScriptOperation(int numArgs, Fields fieldDeclaration, String block, Class returnType,
			String[] parameterNames, Class[] parameterTypes) {
		super(numArgs, fieldDeclaration);
		this.parameterNames = parameterNames == null ? null : Arrays.copyOf(parameterNames, parameterNames.length);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;
		this.parameterTypes = Arrays.copyOf(parameterTypes, parameterTypes.length);

		if (getParameterNamesInternal().length != getParameterTypesInternal().length)
			throw new IllegalArgumentException("parameterNames must be same length as parameterTypes");
	}

	public ScriptOperation(int numArgs, String block, Class returnType) {
		super(numArgs);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;
	}

	public ScriptOperation(int numArgs, String block, Class returnType, Class[] expectedTypes) {
		super(numArgs);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;

		if (expectedTypes == null || expectedTypes.length == 0)
			throw new IllegalArgumentException("expectedTypes may not be null or empty");

		this.parameterTypes = Arrays.copyOf(expectedTypes, expectedTypes.length);
	}

	public ScriptOperation(int numArgs, String block, Class returnType, String[] parameterNames, Class[] parameterTypes) {
		super(numArgs);
		this.parameterNames = parameterNames == null ? null : Arrays.copyOf(parameterNames, parameterNames.length);
		this.block = block;
		this.returnType = returnType == null ? this.returnType : returnType;
		this.parameterTypes = Arrays.copyOf(parameterTypes, parameterTypes.length);

		if (getParameterNamesInternal().length != getParameterTypesInternal().length)
			throw new IllegalArgumentException("parameterNames must be same length as parameterTypes");
	}

	protected String getBlock() {
		return block;
	}

	private boolean hasParameterNames() {
		return parameterNames != null;
	}

	public String[] getParameterNames() {
		return Util.copy(parameterNames);
	}

	private String[] getParameterNamesInternal() {
		if (parameterNames != null)
			return parameterNames;

		try {
			parameterNames = guessParameterNames();
		} catch (IOException exception) {
			throw new OperationException("could not read expression: " + block, exception);
		} catch (CompileException exception) {
			throw new OperationException("could not compile expression: " + block, exception);
		}

		return parameterNames;
	}

	protected String[] guessParameterNames() throws CompileException, IOException {
		throw new OperationException("parameter names are required");
	}

	private Fields getParameterFields() {
		return makeFields(getParameterNamesInternal());
	}

	private boolean hasParameterTypes() {
		return parameterTypes != null;
	}

	public Class[] getParameterTypes() {
		return Util.copy(parameterTypes);
	}

	private Class[] getParameterTypesInternal() {
		if (!hasParameterNames())
			return parameterTypes;

		if (hasParameterNames() && parameterNames.length == parameterTypes.length)
			return parameterTypes;

		if (parameterNames.length > 0 && parameterTypes.length != 1)
			throw new IllegalStateException("wrong number of parameter types, expects: " + parameterNames.length);

		Class[] types = new Class[parameterNames.length];

		Arrays.fill(types, parameterTypes[0]);

		parameterTypes = types;

		return parameterTypes;
	}

	protected ScriptEvaluator getEvaluator(Class returnType, String[] parameterNames, Class[] parameterTypes) {
		try {
			return new ScriptEvaluator(block, returnType, parameterNames, parameterTypes);
		} catch (CompileException exception) {
			throw new OperationException("could not compile script: " + block, exception);
		}
	}

	private Fields makeFields(String[] parameters) {
		Comparable[] fields = new Comparable[parameters.length];

		for (int i = 0; i < parameters.length; i++) {
			String parameter = parameters[i];

			if (parameter.startsWith("$"))
				fields[i] = parse(parameter);
			else
				fields[i] = parameter;
		}

		return new Fields(fields);
	}

	private Comparable parse(String parameter) {
		try {
			return Integer.parseInt(parameter.substring(1));
		} catch (NumberFormatException exception) {
			return parameter;
		}
	}

	public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
		if (operationCall.getContext() == null)
			operationCall.setContext(new Context());

		Context context = operationCall.getContext();

		Fields argumentFields = operationCall.getArgumentFields();

		if (hasParameterNames() && hasParameterTypes()) {
			context.parameterNames = getParameterNamesInternal();
			context.parameterFields = argumentFields.select(getParameterFields()); // inherit argument types
			context.parameterTypes = getParameterTypesInternal();
		} else if (hasParameterTypes()) {
			context.parameterNames = toNames(argumentFields);
			context.parameterFields = argumentFields.applyTypes(getParameterTypesInternal());
			context.parameterTypes = getParameterTypesInternal();
		} else {
			context.parameterNames = toNames(argumentFields);
			context.parameterFields = argumentFields;
			context.parameterTypes = argumentFields.getTypesClasses();

			if (context.parameterTypes == null)
				throw new IllegalArgumentException("field types may not be empty");
		}

		context.parameterCoercions = Coercions.coercibleArray(context.parameterFields);
		context.parameterArray = new Object[context.parameterTypes.length];
		context.scriptEvaluator = getEvaluator(getReturnType(), context.parameterNames, context.parameterTypes);
		context.intermediate = TupleViews.createNarrow(argumentFields.getPos(context.parameterFields));
		context.result = Tuple.size(1);

		// For Performance Tuning
		try {
			MetricsTimer.init();
		} catch (Exception e) {
			// never go into
		}
		scope = MetricsTimer.startScope(CLASS_NAME + ":" + block);
		scope.open();

	}

	public void cleanup(FlowProcess flowProcess, OperationCall<Context> operationCall) {

		scope.close();

	}

	private String[] toNames(Fields argumentFields) {
		String[] names = new String[argumentFields.size()];

		for (int i = 0; i < names.length; i++) {
			Comparable comparable = argumentFields.get(i);
			if (comparable instanceof String)
				names[i] = (String) comparable;
			else
				names[i] = "$" + comparable;
		}

		return names;
	}

	public Class getReturnType() {
		return returnType;
	}

	protected Object evaluate(Context context, TupleEntry input) {
		try {
			scope.begin();
			if (context.parameterTypes.length == 0)
				return context.scriptEvaluator.evaluate(null);

			Tuple parameterTuple = TupleViews.reset(context.intermediate, input.getTuple());
			Object[] arguments = Tuples.asArray(parameterTuple, context.parameterCoercions, context.parameterTypes,
					context.parameterArray);

			Object o = context.scriptEvaluator.evaluate(arguments);
			scope.end();
			return o;
		} catch (InvocationTargetException exception) {
			List<Object> eles = Tuple.elements(input.getTuple());
			Object[] os = eles.toArray(new Object[eles.size()]);

			StringBuffer buffer = new StringBuffer();
			int count = 0;
			for (int i = 0; i < os.length; i++) {
				Object s = os[i];
				if (count != 0)
					buffer.append(",\n");
				if (s == null) {
					buffer.append("[" + context.parameterNames[i] + "] Value: null");
				} else {
					buffer.append("[" + context.parameterNames[i] + "] Value:" + s + "	Type:"
							+ s.getClass().getSimpleName());
				}
				count++;
			}

			throw new OperationException("could not evaluate expression: " + block + "\nwith\n" + buffer.toString(),
					exception.getTargetException());
		} catch (java.lang.IllegalArgumentException e) {
			List<Object> eles = Tuple.elements(input.getTuple());
			Object[] os = eles.toArray(new Object[eles.size()]);

			StringBuffer buffer = new StringBuffer();
			int count = 0;
			for (int i = 0; i < os.length; i++) {
				Object s = os[i];
				if (count != 0)
					buffer.append(",\n");
				if (s == null) {
					buffer.append("[" + context.parameterNames[i] + "] Value:" + "null" + " Expected:"
							+ context.parameterTypes[i].getSimpleName());
				} else {
					buffer.append("[" + context.parameterNames[i] + "] Value:" + s.getClass().getSimpleName()
							+ " Expected:" + context.parameterTypes[i].getSimpleName());
				}
				count++;
			}

			throw new OperationException("could not cast expression: " + block + "\nwith\n" + buffer.toString(),
					e.getCause());
		}
	}

	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof ScriptOperation))
			return false;
		if (!super.equals(object))
			return false;

		ScriptOperation that = (ScriptOperation) object;

		if (block != null ? !block.equals(that.block) : that.block != null)
			return false;
		if (!Arrays.equals(parameterNames, that.parameterNames))
			return false;
		if (!Arrays.equals(parameterTypes, that.parameterTypes))
			return false;

		return true;
	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (block != null ? block.hashCode() : 0);
		result = 31 * result + (parameterTypes != null ? Arrays.hashCode(parameterTypes) : 0);
		result = 31 * result + (parameterNames != null ? Arrays.hashCode(parameterNames) : 0);
		return result;
	}

	public static class Context {
		private Class[] parameterTypes;
		private ScriptEvaluator scriptEvaluator;
		private Fields parameterFields;
		private CoercibleType[] parameterCoercions;
		private String[] parameterNames;
		private Object[] parameterArray;
		private Tuple intermediate;
		protected Tuple result;
	}
}
