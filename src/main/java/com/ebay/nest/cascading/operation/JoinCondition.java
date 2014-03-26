package com.ebay.nest.cascading.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ScriptEvaluator;

import cascading.flow.FlowProcess;
import cascading.operation.Aggregator;
import cascading.operation.AggregatorCall;
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

public class JoinCondition extends BaseOperation<JoinCondition.Context> implements Aggregator<JoinCondition.Context> {

	private static final long serialVersionUID = 4237884237727348198L;
	private final String block;
	private final Class[] parameterTypes;
	private final String[] parameterNames;
	private final Fields rightFields;

	protected static class Context {

		private Class[] parameterTypes;
		private ScriptEvaluator scriptEvaluator;
		private Fields parameterFields;
		private CoercibleType[] parameterCoercions;
		private String[] parameterNames;
		private Object[] parameterArray;
		private Tuple intermediate;

		private List<TupleEntry> right;
		private List<Boolean> filters;
		private int count;

		public Context() {
			right = new ArrayList();
			filters = new ArrayList();
			count = 0;
		}

		public Context reset() {

			right.clear();
			filters.clear();
			count = 0;
			return this;
		}

	}

	public JoinCondition(Fields fieldDeclaration, String block, Fields rightFields, Fields incomingFields) {
		super(incomingFields.size(), fieldDeclaration);
		this.block = block;
		this.rightFields = rightFields;
		parameterNames = toNames(incomingFields);
		parameterTypes = incomingFields.getTypesClasses();

	}

	private String[] toNames(Fields fields) {
		String[] names = new String[fields.size()];

		for (int i = 0; i < names.length; i++) {
			Comparable comparable = fields.get(i);
			if (comparable instanceof String)
				names[i] = (String) comparable;
			else
				names[i] = "$" + comparable;
		}

		return names;
	}

	private boolean hasParameterNames() {
		return parameterNames != null;
	}

	private boolean hasParameterTypes() {
		return parameterTypes != null;
	}

	private String[] getParameterNamesInternal() {
		if (parameterNames != null)
			return parameterNames;

		throw new OperationException("parameter names are required");

	}

	private Fields getParameterFields() {
		return makeFields(getParameterNamesInternal());
	}

	private Fields makeFields(String[] parameters) {
		Comparable[] fields = new Comparable[parameters.length];

		for (int i = 0; i < parameters.length; i++) {
			String parameter = parameters[i];

			if (parameter.startsWith("$"))
				fields[i] = parse(parameter); // returns parameter if not a
												// number after $
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

	private Class[] getParameterTypesInternal() {
		if (!hasParameterNames())
			return parameterTypes;

		if (hasParameterNames() && parameterNames.length == parameterTypes.length)
			return parameterTypes;

		if (parameterNames.length > 0 && parameterTypes.length != 1)
			throw new IllegalStateException("wrong number of parameter types, expects: " + parameterNames.length);

		return parameterTypes;
	}

	public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
		Context context = new Context();

		Fields argumentFields = operationCall.getArgumentFields();

		if (hasParameterNames() && hasParameterTypes()) {
			context.parameterNames = getParameterNamesInternal();
			context.parameterFields = argumentFields.select(getParameterFields());
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

		try {
			context.scriptEvaluator = new ScriptEvaluator(block, Boolean.class, context.parameterNames,
					context.parameterTypes);
		} catch (CompileException e) {
			throw new OperationException("could not compile script: " + block, e);
		}

		context.intermediate = TupleViews.createNarrow(argumentFields.getPos(context.parameterFields));

		operationCall.setContext(context);
	}

	@Override
	public void start(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		aggregatorCall.getContext().reset();
	}

	@Override
	public void aggregate(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		TupleEntry input = aggregatorCall.getArguments();

		Boolean condn;
		try {
			if (parameterTypes.length == 0)
				context.scriptEvaluator.evaluate(null);

			Tuple parameterTuple = TupleViews.reset(context.intermediate, input.getTuple());
			Object[] arguments = Tuples.asArray(parameterTuple, context.parameterCoercions, context.parameterTypes,
					context.parameterArray);

			condn = (Boolean) context.scriptEvaluator.evaluate(arguments);

		} catch (InvocationTargetException exception) {
			List<Object> elements = Tuple.elements(input.getTuple());
			Object[] os = elements.toArray(new Object[elements.size()]);

			StringBuffer buffer = new StringBuffer();
			int count = 0;
			for (int j = 0; j < os.length; j++) {
				Object s = os[j];
				if (count != 0)
					buffer.append(",\n");
				if (s == null) {
					buffer.append("[" + context.parameterNames[j] + "] Value: null");
				} else {
					buffer.append("[" + context.parameterNames[j] + "] Value:" + s + "	Type:"
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
			for (int j = 0; j < os.length; j++) {
				Object s = os[j];
				if (count != 0)
					buffer.append(",\n");
				if (s == null) {
					buffer.append("[" + context.parameterNames[j] + "] Value:" + "null" + " Expected:"
							+ context.parameterTypes[j].getSimpleName());
				} else {
					buffer.append("[" + context.parameterNames[j] + "] Value:" + s.getClass().getSimpleName()
							+ " Expected:" + context.parameterTypes[j].getSimpleName());
				}
				count++;
			}

			throw new OperationException("could not cast expression: " + block + "\nwith\n" + buffer.toString(),
					e.getCause());
		}

		Tuple rs = new Tuple();
		for (int i = 0; i < rightFields.size(); i++) {
			Object o = input.getObject(rightFields.get(i));
			rs.add(o);
		}

		context.right.add(new TupleEntry(rs));
		context.count++;
		if (condn == null) {
			condn = Boolean.FALSE;
		}
		context.filters.add(condn);

	}

	@Override
	public void complete(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		List<Boolean> filters = context.filters;

		boolean isall = true;
		for (int k = 0; k < filters.size(); k++) {
			Boolean filter = filters.get(k);
			if (filter) {
				isall = false;
				aggregatorCall.getOutputCollector().add(context.right.get(k).getTuple());
			}
		}
		if (isall) {
			Tuple res = new Tuple();
			for (int i = 0; i < rightFields.size(); i++) {
				res.add(null);
			}
			aggregatorCall.getOutputCollector().add(res);
		}

	}

}
