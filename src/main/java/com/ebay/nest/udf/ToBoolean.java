package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.BOOLEAN;

public class ToBoolean extends UDF<BOOLEAN> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}
		return Boolean.class;

	}

	public static Boolean evaluate(Object[] args) {
		return null;
	}

	public BOOLEAN exec(Data[] input) throws IOException {
		Data o1 = input[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toBoolean(o1);
	}

}
