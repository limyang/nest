package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.CHAR;

public class ToChar extends UDF<CHAR> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}

		return CHAR.class;
	}

	public static CHAR evaluate(Object[] args) {

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toChar(o1);
	}

	public CHAR exec(Data[] input) throws IOException {
		Object o = input[0];
		if (o == null) {
			return null;
		}
		return CastUtil.toChar(o);
	}

}
