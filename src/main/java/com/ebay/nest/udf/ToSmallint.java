package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.SMALLINT;

public class ToSmallint extends UDF<SMALLINT> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1) {

			throw new SemanticException();
		}

		return SMALLINT.class;
	}

	public static SMALLINT evaluate(Object[] args) {

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toSmallInt(o1);
	}

	public SMALLINT exec(Data[] input) throws IOException {
		Data o = input[0];
		if (o == null) {
			return null;
		}
		return CastUtil.toSmallInt(o);
	}

}
