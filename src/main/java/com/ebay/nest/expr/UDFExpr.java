package com.ebay.nest.expr;

import java.util.List;

import com.ebay.nest.FuncSpec;
import com.ebay.nest.NestContext;
import com.ebay.nest.UDF;

public class UDFExpr extends ExprDesc {

	private static final long serialVersionUID = 1L;
	private transient UDF func;

	private FuncSpec funcSpec;
	private List<ExprDesc> inp;

	public UDFExpr(List<ExprDesc> inp, FuncSpec funcSpec) {

		this.inp = inp;
		this.funcSpec = funcSpec;
		instantiateFunc(funcSpec);
	}

	private void instantiateFunc(FuncSpec fSpec) {
		this.func = (UDF) NestContext.instantiateFuncFromSpec(fSpec);

		this.func.setReporter(getReporter());
		this.func.setNestLogger(nestLogger);
	}

	public List<ExprDesc> getParams() {
		return inp;
	}

	public UDF getFunc() {
		if (func == null) {
			instantiateFunc(funcSpec);
		}
		return func;
	}

	public boolean visit(ExprVisitor v) {
		return v.visitUDF(this);
	}

	public List<ExprDesc> getChildren() {
		return inp;
	}

	public boolean supportsMultipleInputs() {

		return true;
	}

	public String getName() {
		return "UDF" + "(" + func.getClass().getName() + ")" + "[" + resultType.getSimpleName() + "]" + " - " + getId();
	}

}
