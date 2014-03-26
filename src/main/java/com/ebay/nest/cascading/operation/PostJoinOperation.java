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

import com.ebay.nest.Data;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.expr.TupleExprEval;
import com.ebay.nest.primitive.BOOLEAN;

public class PostJoinOperation extends BaseOperation<PostJoinOperation.Context> implements
		Aggregator<PostJoinOperation.Context> {

	private static final long serialVersionUID = 1L;
	private final TupleExprEval[] evals;
	private final Fields leftFields;
	private final Fields rightFields;
	private final Tuple nullTuple;

	protected static class Context {

		private List<TupleEntry> rights;
		private List<TupleEntry> lefts;
		private List<Boolean> filters;
		private int count;

		public Context() {
			rights = new ArrayList();
			lefts = new ArrayList();
			filters = new ArrayList();
			count = 0;
		}

		public Context reset() {

			rights.clear();
			lefts.clear();
			filters.clear();
			count = 0;
			return this;
		}

		public int getCount() {
			return count;
		}

	}

	public PostJoinOperation(List<ExprDesc> ops, Fields leftFields, Fields rightFields, Fields fieldDeclaration) {
		super(ANY, fieldDeclaration);
		List<TupleExprEval> evals = new ArrayList<TupleExprEval>();
		for (ExprDesc op : ops) {
			TupleExprEval eval = new TupleExprEval(op);
			evals.add(eval);
		}
		this.evals = evals.toArray(new TupleExprEval[evals.size()]);
		this.leftFields = leftFields;
		this.rightFields = rightFields;

		nullTuple = new Tuple();
		for (int i = 0; i < rightFields.size(); i++) {
			nullTuple.add(null);
		}
	}

	public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
		operationCall.setContext(new Context());
	}

	@Override
	public void start(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		aggregatorCall.getContext().reset();
	}

	@Override
	public void aggregate(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		TupleEntry input = aggregatorCall.getArguments();
		Boolean pass = Boolean.TRUE;
		for (TupleExprEval exprTuple : evals) {
			Data res = exprTuple.compute(input);
			BOOLEAN b = CastUtil.toBoolean(res);
			boolean bool = b.getBoolean();
			if (!bool) {
				pass = Boolean.FALSE;
				break;
			}
		}
		context.filters.add(pass);
		Tuple rs = new Tuple();
		for (int i = 0; i < leftFields.size(); i++) {
			Object o = input.getObject(leftFields.get(i));
			rs.add(o);
		}
		context.lefts.add(new TupleEntry(rs));

		rs = new Tuple();
		for (int i = 0; i < rightFields.size(); i++) {
			Object o = input.getObject(rightFields.get(i));
			rs.add(o);
		}
		context.rights.add(new TupleEntry(rs));

		context.count++;

	}

	@Override
	public void complete(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
		Context context = aggregatorCall.getContext();
		List<Boolean> filters = context.filters;
		Tuple res = new Tuple();

		Tuple last = null;
		int num = 0;
		int flag = 0;
		for (int k = 0; k < filters.size(); k++) {
			Boolean filter = filters.get(k);
			Tuple l = context.lefts.get(k).getTuple();
			Tuple r = context.rights.get(k).getTuple();
			if (!l.equals(last)) {
				if (num == 0 && last != null) {
					res = last.append(nullTuple);
					aggregatorCall.getOutputCollector().add(res);
					//flag++;
				}
				num = 0;
			}

			if (filter) {
				res = l.append(r);
				aggregatorCall.getOutputCollector().add(res);
				num++;
				//flag++;
			}
			last = l;

		}
		if (num == 0 && last != null) {
			res = last.append(nullTuple);
			aggregatorCall.getOutputCollector().add(res);
		}

	}

}
