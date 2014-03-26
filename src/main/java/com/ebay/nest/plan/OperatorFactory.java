package com.ebay.nest.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ebay.nest.analyzer.RowSchema;

public final class OperatorFactory {

	/**
	 * OpTuple.
	 * 
	 * @param <T>
	 */
	public static final class OpTuple<T extends OperatorDesc> {
		public Class<T> descClass;
		public Class<? extends Operator<T>> opClass;

		public OpTuple(Class<T> descClass, Class<? extends Operator<T>> opClass) {
			this.descClass = descClass;
			this.opClass = opClass;
		}
	}

	public static ArrayList<OpTuple> opvec;
	static {
		opvec = new ArrayList<OpTuple>();
		opvec.add(new OpTuple<FilterDesc>(FilterDesc.class, FilterOperator.class));
		opvec.add(new OpTuple<SelectDesc>(SelectDesc.class, SelectOperator.class));
		opvec.add(new OpTuple<ForwardDesc>(ForwardDesc.class, ForwardOperator.class));
		opvec.add(new OpTuple<FileSinkDesc>(FileSinkDesc.class, FileSinkOperator.class));
		opvec.add(new OpTuple<CollectDesc>(CollectDesc.class, CollectOperator.class));
		opvec.add(new OpTuple<ScriptDesc>(ScriptDesc.class, ScriptOperator.class));
		opvec.add(new OpTuple<PTFDesc>(PTFDesc.class, PTFOperator.class));
		opvec.add(new OpTuple<ReduceSinkDesc>(ReduceSinkDesc.class, ReduceSinkOperator.class));
		opvec.add(new OpTuple<ExtractDesc>(ExtractDesc.class, ExtractOperator.class));
		opvec.add(new OpTuple<GroupByDesc>(GroupByDesc.class, GroupByOperator.class));
		opvec.add(new OpTuple<JoinDesc>(JoinDesc.class, JoinOperator.class));
		opvec.add(new OpTuple<MapJoinDesc>(MapJoinDesc.class, MapJoinOperator.class));
		opvec.add(new OpTuple<SMBJoinDesc>(SMBJoinDesc.class, SMBMapJoinOperator.class));
		opvec.add(new OpTuple<LimitDesc>(LimitDesc.class, LimitOperator.class));
		opvec.add(new OpTuple<TableScanDesc>(TableScanDesc.class, TableScanOperator.class));
		opvec.add(new OpTuple<UnionDesc>(UnionDesc.class, UnionOperator.class));
		opvec.add(new OpTuple<UDTFDesc>(UDTFDesc.class, UDTFOperator.class));
		opvec.add(new OpTuple<LateralViewJoinDesc>(LateralViewJoinDesc.class, LateralViewJoinOperator.class));
		opvec.add(new OpTuple<LateralViewForwardDesc>(LateralViewForwardDesc.class, LateralViewForwardOperator.class));
		opvec.add(new OpTuple<HashTableDummyDesc>(HashTableDummyDesc.class, HashTableDummyOperator.class));
		opvec.add(new OpTuple<HashTableSinkDesc>(HashTableSinkDesc.class, HashTableSinkOperator.class));
		opvec.add(new OpTuple<DummyStoreDesc>(DummyStoreDesc.class, DummyStoreOperator.class));
		opvec.add(new OpTuple<DemuxDesc>(DemuxDesc.class, DemuxOperator.class));
		opvec.add(new OpTuple<MuxDesc>(MuxDesc.class, MuxOperator.class));
	}

	public static <T extends OperatorDesc> Operator<T> get(Class<T> opClass) {

		for (OpTuple o : opvec) {
			if (o.descClass == opClass) {
				try {
					Operator<T> op = (Operator<T>) o.opClass.newInstance();
					op.initializeCounters();
					return op;
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException("No operator for descriptor class " + opClass.getName());
	}

	public static <T extends OperatorDesc> Operator<T> get(Class<T> opClass, RowSchema rwsch) {

		Operator<T> ret = get(opClass);
		ret.setSchema(rwsch);
		return ret;
	}

	/**
	 * Returns an operator given the conf and a list of children operators.
	 */
	public static <T extends OperatorDesc> Operator<T> get(T conf, Operator<? extends OperatorDesc>... oplist) {
		Operator<T> ret = get((Class<T>) conf.getClass());
		ret.setConf(conf);
		makeChild(ret, oplist);
		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of children operators.
	 */
	public static void makeChild(Operator<? extends OperatorDesc> ret, Operator<? extends OperatorDesc>... oplist) {
		if (oplist.length == 0) {
			return;
		}

		ArrayList<Operator<? extends OperatorDesc>> clist = new ArrayList<Operator<? extends OperatorDesc>>();
		for (Operator<? extends OperatorDesc> op : oplist) {
			clist.add(op);
		}
		ret.setChildOperators(clist);

		// Add this parent to the children
		for (Operator<? extends OperatorDesc> op : oplist) {
			List<Operator<? extends OperatorDesc>> parents = op.getParentOperators();
			if (parents == null) {
				parents = new ArrayList<Operator<? extends OperatorDesc>>();
			}
			parents.add(ret);
			op.setParentOperators(parents);
		}
	}

	/**
	 * Returns an operator given the conf and a list of children operators.
	 */
	public static <T extends OperatorDesc> Operator<T> get(T conf, RowSchema rwsch, Operator... oplist) {
		Operator<T> ret = get(conf, oplist);
		ret.setSchema(rwsch);
		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf, Operator... oplist) {
		Operator<T> ret = get((Class<T>) conf.getClass());
		ret.setConf(conf);
		if (oplist.length == 0) {
			return (ret);
		}

		// Add the new operator as child of each of the passed in operators
		for (Operator op : oplist) {
			List<Operator> children = op.getChildOperators();
			if (children == null) {
				children = new ArrayList<Operator>();
			}
			children.add(ret);
			op.setChildOperators(children);
		}

		// add parents for the newly created operator
		List<Operator<? extends OperatorDesc>> parent = new ArrayList<Operator<? extends OperatorDesc>>();
		for (Operator op : oplist) {
			parent.add(op);
		}

		ret.setParentOperators(parent);

		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf,
			List<Operator<? extends OperatorDesc>> oplist) {
		Operator<T> ret = get((Class<T>) conf.getClass());
		ret.setConf(conf);
		if (oplist.size() == 0) {
			return (ret);
		}

		// Add the new operator as child of each of the passed in operators
		for (Operator op : oplist) {
			List<Operator> children = op.getChildOperators();
			if (children == null) {
				children = new ArrayList<Operator>();
			}
			children.add(ret);
			op.setChildOperators(children);
		}

		// add parents for the newly created operator
		List<Operator<? extends OperatorDesc>> parent = new ArrayList<Operator<? extends OperatorDesc>>();
		for (Operator op : oplist) {
			parent.add(op);
		}

		ret.setParentOperators(parent);

		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf, RowSchema rwsch, Operator... oplist) {
		Operator<T> ret = getAndMakeChild(conf, oplist);
		ret.setSchema(rwsch);
		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf, RowSchema rwsch,
			Map<String, ExprNodeDesc> colExprMap, Operator... oplist) {
		Operator<T> ret = getAndMakeChild(conf, rwsch, oplist);
		ret.setColumnExprMap(colExprMap);
		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf, RowSchema rwsch,
			List<Operator<? extends OperatorDesc>> oplist) {
		Operator<T> ret = getAndMakeChild(conf, oplist);
		ret.setSchema(rwsch);
		return (ret);
	}

	/**
	 * Returns an operator given the conf and a list of parent operators.
	 */
	public static <T extends OperatorDesc> Operator<T> getAndMakeChild(T conf, RowSchema rwsch,
			Map<String, ExprNodeDesc> colExprMap, List<Operator<? extends OperatorDesc>> oplist) {
		Operator<T> ret = getAndMakeChild(conf, rwsch, oplist);
		ret.setColumnExprMap(colExprMap);
		return (ret);
	}

	private OperatorFactory() {
		// prevent instantiation
	}
}
