package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CompatibleUtil;

public class Coalesce extends UDF<Data> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 2) {
			throw new SemanticException("Function(Coalesce) should have two parameters");
		}
		Class res = CompatibleUtil.getCompatiableForType(params[0], params[1]);
		if (res == null) {
			throw new SemanticException("The Parameters at Function(Coalesce) is not compatible!");
		}

		return res;
	}

	public static Object evaluate(Object[] args) {
		if (args[0] == null) {
			return args[1];
		}
		return args[0];
	}

	public Data exec(Data[] input) throws IOException {
		if (input[0] == null) {
			return input[1];
		}
		return input[0];
	}

}
