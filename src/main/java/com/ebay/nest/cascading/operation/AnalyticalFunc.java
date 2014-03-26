package com.ebay.nest.cascading.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

import com.ebay.nest.Pair;
import com.ebay.nest.analyzer.AnalyticFuncType;
import com.ebay.nest.analyzer.OrderType;
import com.ebay.nest.arithmetic.CompareUtil;

public class AnalyticalFunc extends BaseOperation<AnalyticalFunc.Context> implements Aggregator<AnalyticalFunc.Context> {

	private static final long serialVersionUID = -5563524443747740139L;
	private final AnalyticFuncType[] types;
	private final String[] blocks;
	private final String[][] orders;
	private final OrderType[][] orderTypes;
	private final Class[] returnClasses;
	private final Class[][] innerClasses;
	private final Class[] parameterTypes;
	private final String[] parameterNames;
	private final int[] ups;
	private final int[] downs;

	protected static class Context {

		private Class[] parameterTypes;
		private ScriptEvaluator[] scriptEvaluator;
		private Fields parameterFields;
		private CoercibleType[] parameterCoercions;
		private String[] parameterNames;
		private Object[] parameterArray;
		private Tuple intermediate;

		private List<Tuple[]> analyticals;

		private List<TupleEntry> origin;

		private List<Object[][]> orders;

		int size;

		public Context(int size) {
			analyticals = new ArrayList<Tuple[]>();
			this.size = size;
			origin = new ArrayList();
			orders = new ArrayList();

		}

		public Context reset() {

			analyticals.clear();
			origin.clear();
			orders.clear();
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

	public AnalyticalFunc(Fields fieldDeclaration, AnalyticFuncType[] types, String[] blocks, String[][] orders,
			OrderType[][] orderTypes, Class[] returnClasses, Class[][] innerClasses, int[] ups, int[] downs,
			Fields incomingFields) {
		super(incomingFields.size(), fieldDeclaration);
		this.types = types == null ? null : Arrays.copyOf(types, types.length);
		this.blocks = blocks == null ? null : Arrays.copyOf(blocks, blocks.length);
		this.returnClasses = returnClasses == null ? null : Arrays.copyOf(returnClasses, returnClasses.length);
		this.innerClasses = innerClasses == null ? null : Arrays.copyOf(innerClasses, innerClasses.length);
		this.ups = ups == null ? null : Arrays.copyOf(ups, ups.length);
		this.downs = downs == null ? null : Arrays.copyOf(downs, downs.length);
		this.orders = orders == null ? null : Arrays.copyOf(orders, orders.length);
		this.orderTypes = orderTypes == null ? null : Arrays.copyOf(orderTypes, orders.length);

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
		Context context = new Context(types.length);

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
		List<ScriptEvaluator> evaluators = new ArrayList<ScriptEvaluator>();

		for (int i = 0; i < blocks.length; i++) {

			try {
				evaluators.add(new ScriptEvaluator(blocks[i], Tuple.class, context.parameterNames,
						context.parameterTypes));
			} catch (Exception e) {
				throw new OperationException("could not compile script: " + blocks[i], e);
			}

		}
		context.scriptEvaluator = evaluators.toArray(new ScriptEvaluator[evaluators.size()]);

		context.intermediate = TupleViews.createNarrow(argumentFields.getPos(context.parameterFields));

		operationCall.setContext(context);
	}

	public void start(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		aggregatorCall.getContext().reset();

	}

	public void aggregate(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		TupleEntry input = aggregatorCall.getArguments();

		TupleEntry entry = new TupleEntry(input);
		List<Tuple> analytical = new ArrayList<Tuple>();
		context.origin.add(entry);
		Object[][] oss = new Object[types.length][];
		for (int i = 0; i < types.length; i++) {
			Tuple output;
			try {
				if (parameterTypes.length == 0)
					context.scriptEvaluator[i].evaluate(null);

				Tuple parameterTuple = TupleViews.reset(context.intermediate, input.getTuple());
				Object[] arguments = Tuples.asArray(parameterTuple, context.parameterCoercions, context.parameterTypes,
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
						buffer.append("[" + context.parameterNames[j] + "] Value: null");
					} else {
						buffer.append("[" + context.parameterNames[j] + "] Value:" + s + "	Type:"
								+ s.getClass().getSimpleName());
					}
					count++;
				}

				throw new OperationException("could not evaluate expression: " + blocks[i] + "\nwith\n"
						+ buffer.toString(), exception.getTargetException());
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

				throw new OperationException(
						"could not cast expression: " + blocks[i] + "\nwith\n" + buffer.toString(), e.getCause());
			}
			analytical.add(output);
			if (orders[i] != null) {
				Object[] os = new Object[orders[i].length];
				for (int j = 0; j < orders[i].length; j++) {
					os[j] = input.getObject(orders[i][j]);
				}
				oss[i] = os;
			}
		}
		context.analyticals.add(analytical.toArray(new Tuple[analytical.size()]));
		context.orders.add(oss);

	}

	public class MyComparable implements Comparator<Pair<Integer, Object[]>> {

		private OrderType[] ots;

		public MyComparable(OrderType[] ots) {
			this.ots = ots;
		}

		public int compare(Pair<Integer, Object[]> p1, Pair<Integer, Object[]> p2) {
			Object[] os1 = p1.right;
			Object[] os2 = p2.right;
			if (os1 == null && os2 != null) {
				return -1;
			}
			if (os1 != null && os2 == null) {
				return 1;
			}
			if (os1 == null && os2 == null) {
				return 0;
			}
			int size = os1.length;
			for (int i = 0; i < size; i++) {
				Object o1 = os1[i];
				Object o2 = os2[i];
				OrderType ot = ots[i];
				switch (ot) {
				case ASC:
					if (o1 != null && o2 == null) {
						return 1;
					} else if (o1 == null && o2 != null) {
						return -1;
					} else if (o1 == null && o2 == null) {
						continue;
					} else {
						Integer com = CompareUtil.compareTo(o1, o2);
						if (com != 0) {
							return com;
						} else
							continue;
					}

				case DESC:

					if (o1 != null && o2 == null) {
						return -1;
					} else if (o1 == null && o2 != null) {
						return 1;
					} else if (o1 == null && o2 == null) {
						continue;
					} else {
						Integer com = CompareUtil.compareTo(o1, o2);
						if (com == 0) {
							continue;
						} else if (com<0){
							return 1;
						} else if (com>0){
							return -1;
						}
					}
				}
			}

			return 0;
		}

	}

	public int[] getMap(Object[][][] oss, int k) {

		List<Pair<Integer, Object[]>> osList = new ArrayList<Pair<Integer, Object[]>>();
		for (int i = 0; i < oss.length; i++) {
			Object[][] os = oss[i];
			Object[] o = os[k];
			Pair<Integer, Object[]> p = new Pair<Integer, Object[]>(i, o);
			osList.add(p);
		}
		Collections.sort(osList, new MyComparable(orderTypes[k]));
		int[] res = new int[osList.size()];
		for (int i = 0; i < osList.size(); i++) {
			Pair<Integer, Object[]> p = osList.get(i);
			int index = p.getLeft();
			res[index] = i+1;
		}
		return res;

	}

	public void complete(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		List<TupleEntry> events = context.origin;
		int size = events.size();
		Tuple[][] tuples = context.analyticals.toArray(new Tuple[context.analyticals.size()][]);
		Object[][][] os = context.orders.toArray(new Object[context.orders.size()][][]);

		int[][] maps = new int[types.length][];
		for (int j = 0; j < types.length; j++) {
			maps[j] = getMap(os, j);
		}
		for (int line = 0; line < events.size(); line++) {
			Tuple result = new Tuple();
			result.addAll(events.get(line).getTuple());
			for (int j = 0; j < types.length; j++) {
				int first = 0;
				if (ups[j] != -1) {
					first = line - ups[j];
				}
				if (first < 0) {
					first = 0;
				}
				int last = size - 1;
				if (downs[j] != -1) {
					last = line + downs[j];
				}
				if (last > size - 1) {
					last = size - 1;
				}
				Object o;
				switch (types[j]) {
				case AVG:
					o = AnalyticalFuncUtil.computerAvg(tuples, first, last, j, innerClasses[j], returnClasses[j]);
					break;
				case SUM:
					o = AnalyticalFuncUtil.computerSum(tuples, first, last, j, innerClasses[j], returnClasses[j]);
					break;
				case ROW_NUMBER:
					o = AnalyticalFuncUtil.countForRow(tuples, maps[j], line, j, innerClasses[j], returnClasses[j]);
					break;
				case MAX:
					o = AnalyticalFuncUtil.computerMax(tuples, first, last, j, innerClasses[j], returnClasses[j]);
					break;
				default:
					o = null;
					break;

				}
				result.add(o);
			}
			aggregatorCall.getOutputCollector().add(result);
		}

	}
}
