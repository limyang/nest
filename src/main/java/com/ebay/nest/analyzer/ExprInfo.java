package com.ebay.nest.analyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cascading.tuple.Fields;

public class ExprInfo {

	private String script;
	private Class returnType;
	private ExprType op;
	private Fields fields;
	private Map<Fields, AggregateFuncInfo> aggregators = new HashMap<Fields, AggregateFuncInfo>();
	private Map<Fields, AnalyticFuncInfo> Analyticals = new HashMap<Fields, AnalyticFuncInfo>();

	public void setFieldsAggregator(Fields f, AggregateFuncInfo a) {

		aggregators.put(f, a);
	}

	public Set<Fields> getAggregatorFields() {

		return aggregators.keySet();
	}

	public AggregateFuncInfo getAggregatorForField(Fields f) {

		return aggregators.get(f);
	}

	public void setFieldsAnalytical(Fields f, AnalyticFuncInfo a) {

		Analyticals.put(f, a);
	}

	public Set<Fields> getAnalyticalFields() {

		return Analyticals.keySet();
	}

	public AnalyticFuncInfo getAnalyticalForField(Fields f) {

		return Analyticals.get(f);
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Class getReturnType() {
		return returnType;
	}

	public void setReturnType(Class type) {
		this.returnType = type;
	}

	public ExprType getOp() {
		return op;
	}

	public void setOp(ExprType op) {
		this.op = op;
	}

	public ExprInfo(String script, Class type, ExprType op, Fields fields) {
		this.script = script;
		this.returnType = type;
		this.op = op;
		this.fields = fields;
	}
	

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}

}
