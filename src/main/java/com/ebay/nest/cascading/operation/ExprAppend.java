package com.ebay.nest.cascading.operation;

import java.util.ArrayList;
import java.util.List;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

import com.ebay.nest.Data;
import com.ebay.nest.expr.ExprDesc;
import com.ebay.nest.expr.TupleExprEval;

public class ExprAppend extends BaseOperation implements Function {

	private static final long serialVersionUID = 1L;
	private TupleExprEval[] evals;

	public ExprAppend(List<ExprDesc> ops, Fields fieldDeclaration) {
		super(ANY, fieldDeclaration);
		List<TupleExprEval> evals = new ArrayList<TupleExprEval>();
		for (ExprDesc op : ops) {
			TupleExprEval eval = new TupleExprEval(op);
			evals.add(eval);
		}
		this.evals = evals.toArray(new TupleExprEval[evals.size()]);

	}

	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		TupleEntry entry = functionCall.getArguments();
		Tuple result = new Tuple();
		result.addAll(entry.getTuple());
		for (TupleExprEval exprTuple : evals) {
			Data res = exprTuple.compute(entry);
			result.add(res);
		}

		functionCall.getOutputCollector().add(result);

	}
}
