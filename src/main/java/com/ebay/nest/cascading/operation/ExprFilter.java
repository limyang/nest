package com.ebay.nest.cascading.operation;

import java.util.ArrayList;
import java.util.List;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Filter;
import cascading.operation.FilterCall;
import cascading.tuple.TupleEntry;

import com.ebay.nest.Data;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.expr.TupleExprEval;
import com.ebay.nest.primitive.BOOLEAN;

public class ExprFilter extends BaseOperation implements Filter {

	private static final long serialVersionUID = 1L;
	private TupleExprEval[] evals;

	public ExprFilter(List<ExprDesc> ops) {
		super(ANY);
		List<TupleExprEval> evals = new ArrayList<TupleExprEval>();
		for (ExprDesc op : ops) {
			TupleExprEval eval = new TupleExprEval(op);
			evals.add(eval);
		}
		this.evals = evals.toArray(new TupleExprEval[evals.size()]);

	}

	public boolean isRemove(FlowProcess flowProcess, FilterCall filterCall) {
		TupleEntry entry = filterCall.getArguments();
		entry = new TupleEntry(entry);

		for (TupleExprEval eval : evals) {
			Data res = eval.compute(entry);
			BOOLEAN b = CastUtil.toBoolean(res);
			boolean bool = b.getBoolean();
			if (!bool) {
				return true;
			}
		}

		return false;
	}
}
