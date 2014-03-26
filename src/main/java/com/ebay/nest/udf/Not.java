package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.BOOLEAN;

public class Not extends UDF<BOOLEAN> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {
			throw new SemanticException("Not Function should have one paramaters");
		}
		if (params[0] != Boolean.class) {
			throw new SemanticException("Not Function Incorrect Used");
		}
		return Boolean.class;
	}

	public static Boolean evaluate(Object[] args) {
		Object arg = args[0];
		if (arg == null) {
			return false;
		}
		Boolean b = (Boolean) arg;
		if (Boolean.FALSE.equals(b)) {
			return true;
		}

		return false;
	}

	public BOOLEAN exec(Data[] input) throws IOException {
		Data arg = input[0];
		if (arg == null) {
			return BOOLEAN.FALSE;
		}
		BOOLEAN b = (BOOLEAN) arg;
		if (b == BOOLEAN.FALSE) {
			return BOOLEAN.TRUE;
		} else if (b == BOOLEAN.TRUE) {
			return BOOLEAN.FALSE;
		}

		return BOOLEAN.FALSE;
	}
}
