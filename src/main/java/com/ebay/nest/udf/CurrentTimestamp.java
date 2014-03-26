package com.ebay.nest.udf;

import java.io.IOException;
import java.sql.Timestamp;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.ErrorMsg;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.TIMESTAMP;

public class CurrentTimestamp extends UDF<TIMESTAMP> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if ((params.length == 1 && params[0] != INTEGER.class) || params.length > 1) {
			throw new SemanticException(ErrorMsg.WRONG_PARAMETER_FOR_EXPR.getMsg());
		}
		return TIMESTAMP.class;
	}

	public static Object evaluate(Object[] args) {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		return new TIMESTAMP(t);
	}

	public static Object evaluate() {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		return new TIMESTAMP(t);
	}

	public TIMESTAMP exec(Data[] input) throws IOException {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		return new TIMESTAMP(t);
	}

}
