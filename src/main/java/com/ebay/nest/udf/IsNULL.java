package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.BOOLEAN;

public class IsNULL extends UDF<BOOLEAN> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class valideParameterType(Class[] params) throws SemanticException {

		if (params.length != 1) {
			throw new SemanticException();
		}

		return Boolean.class;
	}

	public static Boolean evaluate(Object[] args) {
		Object o = args[0];
		if (o == null) {
			return true;
		}

		return false;
	}

	public BOOLEAN exec(Data[] input) throws IOException {
		Data o = input[0];
		if (o == null) {
			return BOOLEAN.TRUE;
		}

		return BOOLEAN.FALSE;
	}

}