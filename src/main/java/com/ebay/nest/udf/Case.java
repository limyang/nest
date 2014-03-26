package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CompareUtil;
import com.ebay.nest.arithmetic.CompatibleUtil;

public class Case extends UDF<Data> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length < 3) {
			throw new SemanticException("CaseWhen have wrong parameter lengths");
		}
		Class caseClass = params[0];
		Class returnClass = params[params.length - 1];

		for (int i = 1; i < params.length - 1; i += 2) {

			Class whenClass = params[i];
			Class thenClass = params[i + 1];
			if (caseClass != whenClass) {
				throw new SemanticException("When Statement at CaseWhen should be the same type");
			}
			returnClass = CompatibleUtil.getCompatiableForType(returnClass, thenClass);
			if (returnClass == null) {
				throw new SemanticException("Then Statement at CaseWhen should be compatible");
			}
		}

		return returnClass;
	}

	public static Object evaluate(Object[] args) {

		int argnum = args.length;
		Object caseObject = args[0];
		Object elseObject = args[argnum - 1];
		if (caseObject == null) {

			return null;
		}
		int i = 0;

		for (i = 1; i < argnum - 1; i += 2) {
			Object whenObject = args[i];
			Object thenObject = args[i + 1];

			if (CompareUtil.compareTo(caseObject, whenObject) == 0) {

				return thenObject;
			} else
				continue;
		}
		if (i == argnum) {
			return null;
		}

		return elseObject;

	}

	public Data exec(Data[] input) throws IOException {
		int argnum = input.length;
		Data caseObject = input[0];
		Data elseObject = input[argnum - 1];
		if (caseObject == null) {
			return null;
		}
		int i = 0;

		for (i = 1; i < argnum - 1; i += 2) {
			Data whenObject = input[i];
			Data thenObject = input[i + 1];

			if (CompareUtil.compareTo(caseObject, whenObject) == 0) {

				return thenObject;
			} else
				continue;
		}
		if (i == argnum) {
			return null;
		}

		return elseObject;
	}

}
