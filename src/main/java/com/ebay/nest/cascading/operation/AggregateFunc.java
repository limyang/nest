package com.ebay.nest.cascading.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.ebay.nest.analyzer.AggregateFuncType;

public class AggregateFunc extends BaseOperation<AggregateFunc.Context>
		implements Aggregator<AggregateFunc.Context> {

	private static final long serialVersionUID = 7189707881324802240L;
	private final AggregateFuncType[] types;
	private final String[] blocks;
	private final Class[] returnClasses;
	private final Class[][] innerClasses;
	private final Class[] parameterTypes;
	private final String[] parameterNames;

	protected static class Context {

		private Class[] parameterTypes;
		private ScriptEvaluator[] scriptEvaluator;
		private Fields parameterFields;
		private CoercibleType[] parameterCoercions;
		private String[] parameterNames;
		private Object[] parameterArray;
		private Tuple intermediate;

		private List<Tuple[]> aggregators;
		int size;

		public Context(int size) {
			aggregators = new ArrayList<Tuple[]>();
			this.size = size;
		}

		public Context reset() {

			aggregators.clear();
			return this;
		}

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

	public AggregateFunc(Fields fieldDeclaration, AggregateFuncType[] types,
			String[] blocks, Class[] returnClasses, Class[][] innerClasses,
			Fields incomingFields) {
		super(incomingFields.size(), fieldDeclaration);
		this.types = types == null ? null : Arrays.copyOf(types, types.length);
		this.blocks = blocks == null ? null : Arrays.copyOf(blocks,
				blocks.length);
		this.returnClasses = returnClasses == null ? null : Arrays.copyOf(
				returnClasses, returnClasses.length);
		this.innerClasses = innerClasses == null ? null : Arrays.copyOf(
				innerClasses, innerClasses.length);

		parameterNames = toNames(incomingFields);
		parameterTypes = incomingFields.getTypesClasses();

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

		if (hasParameterNames()
				&& parameterNames.length == parameterTypes.length)
			return parameterTypes;

		if (parameterNames.length > 0 && parameterTypes.length != 1)
			throw new IllegalStateException(
					"wrong number of parameter types, expects: "
							+ parameterNames.length);

		return parameterTypes;
	}

	public void prepare(FlowProcess flowProcess,
			OperationCall<Context> operationCall) {
		Context context = new Context(types.length);

		Fields argumentFields = operationCall.getArgumentFields();

		if (hasParameterNames() && hasParameterTypes()) {
			context.parameterNames = getParameterNamesInternal();
			context.parameterFields = argumentFields
					.select(getParameterFields());
			context.parameterTypes = getParameterTypesInternal();
		} else if (hasParameterTypes()) {
			context.parameterNames = toNames(argumentFields);
			context.parameterFields = argumentFields
					.applyTypes(getParameterTypesInternal());
			context.parameterTypes = getParameterTypesInternal();
		} else {
			context.parameterNames = toNames(argumentFields);
			context.parameterFields = argumentFields;
			context.parameterTypes = argumentFields.getTypesClasses();

			if (context.parameterTypes == null)
				throw new IllegalArgumentException(
						"field types may not be empty");
		}

		context.parameterCoercions = Coercions
				.coercibleArray(context.parameterFields);
		context.parameterArray = new Object[context.parameterTypes.length];
		List<ScriptEvaluator> evaluators = new ArrayList<ScriptEvaluator>();

		for (int i = 0; i < blocks.length; i++) {

			try {
				evaluators.add(new ScriptEvaluator(blocks[i], Tuple.class,
						context.parameterNames, context.parameterTypes));
			} catch (Exception e) {
				throw new OperationException("could not compile script: "
						+ blocks[i], e);
			}

		}
		context.scriptEvaluator = evaluators
				.toArray(new ScriptEvaluator[evaluators.size()]);

		context.intermediate = TupleViews.createNarrow(argumentFields
				.getPos(context.parameterFields));

		operationCall.setContext(context);
	}

	@Override
	public void start(FlowProcess flowProcess,
			AggregatorCall<Context> aggregatorCall) {
		aggregatorCall.getContext().reset();

	}

	@Override
	public void aggregate(FlowProcess flowProcess,
			AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		TupleEntry input = aggregatorCall.getArguments();
		List<Tuple> analytical = new ArrayList<Tuple>();
		for (int i = 0; i < types.length; i++) {
			Tuple output;
			try {
				if (parameterTypes.length == 0)
					context.scriptEvaluator[i].evaluate(null);

				Tuple parameterTuple = TupleViews.reset(context.intermediate,
						input.getTuple());
				Object[] arguments = Tuples.asArray(parameterTuple,
						context.parameterCoercions, context.parameterTypes,
						context.parameterArray);

				output = (Tuple) context.scriptEvaluator[i].evaluate(arguments);

			} catch (InvocationTargetException exception) {
				List<Object> eles = Tuple.elements(input.getTuple());
				Object[] os = eles.toArray(new Object[eles.size()]);

				StringBuffer buffer = new StringBuffer();
				int count = 0;
				for (int j = 0; j < os.length; j++) {
					Object s = os[j];
					if (count != 0)
						buffer.append(",\n");
					if (s == null) {
						buffer.append("[" + context.parameterNames[j]
								+ "] Value: null");
					} else {
						buffer.append("[" + context.parameterNames[j]
								+ "] Value:" + s + "	Type:"
								+ s.getClass().getSimpleName());
					}
					count++;
				}

				throw new OperationException("could not evaluate expression: "
						+ blocks[i] + "\nwith\n" + buffer.toString(),
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
						buffer.append("[" + context.parameterNames[j]
								+ "] Value:" + "null" + " Expected:"
								+ context.parameterTypes[j].getSimpleName());
					} else {
						buffer.append("[" + context.parameterNames[j]
								+ "] Value:" + s.getClass().getSimpleName()
								+ " Expected:"
								+ context.parameterTypes[j].getSimpleName());
					}
					count++;
				}

				throw new OperationException("could not cast expression: "
						+ blocks[i] + "\nwith\n" + buffer.toString(),
						e.getCause());
			}
			analytical.add(output);

		}

		context.aggregators
				.add(analytical.toArray(new Tuple[analytical.size()]));

	}

	public void complete(FlowProcess flowProcess,
			AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		Tuple[][] tuples = context.aggregators
				.toArray(new Tuple[context.aggregators.size()][]);
		Tuple result = new Tuple();
		for (int j = 0; j < types.length; j++) {

			Object o = null;
			switch (types[j]) {
			case AVG:
				o = AnalyticalFuncUtil.computerAvg(tuples, 0, tuples.length - 1,
						j, innerClasses[j], returnClasses[j]);
				break;
			case SUM:
				o = AnalyticalFuncUtil.computerSum(tuples, 0, tuples.length - 1,
						j, innerClasses[j], returnClasses[j]);
				break;
			case MAX:
				o = AnalyticalFuncUtil.computerMax(tuples, 0, tuples.length - 1,
						j, innerClasses[j], returnClasses[j]);
				break;
			default:
				break;

			}
			result.add(o);
		}
		aggregatorCall.getOutputCollector().add(result);

	}
}
