package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.BIGINT;

public class ToBigint extends UDF<BIGINT> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}

		return BIGINT.class;
	}

	public static BIGINT evaluate(Object[] args) {

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toBigInt(o1);
	}

	public BIGINT exec(Data[] input) throws IOException {
		Data o1 = input[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toBigInt(o1);
	}

}
