package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.FLOAT;

public class ToFloat extends UDF<FLOAT> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}

		return FLOAT.class;
	}

	public static FLOAT evaluate(Object[] args) {

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toFloat(o1);

	}

	public FLOAT exec(Data[] input) throws IOException {
		Data o1 = input[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toFloat(o1);
	}
}
