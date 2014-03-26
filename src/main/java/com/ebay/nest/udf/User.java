package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.CHAR;

public class User extends UDF<CHAR> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 0) {
			throw new SemanticException();
		}
		return CHAR.class;
	}

	public static CHAR evaluate(Object[] args) {

		String s = System.getProperty("user.name");
		return new CHAR(s);
	}

	public static CHAR evaluate() {

		String s = System.getProperty("user.name");
		return new CHAR(s);
	}

	public CHAR exec(Data[] input) throws IOException {
		String s = System.getProperty("user.name");
		return new CHAR(s);
	}

}
