package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.INTEGER;

public class ToInteger extends UDF<INTEGER> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}

		return INTEGER.class;
	}

	public static INTEGER evaluate(Object[] args) {

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toInteger(o1);
	}

	public INTEGER exec(Data[] input) throws IOException {
		Data o = input[0];
		if (o == null) {
			return null;
		}
		return CastUtil.toInteger(o);
	}

}
