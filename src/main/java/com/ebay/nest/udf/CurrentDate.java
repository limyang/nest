package com.ebay.nest.udf;

import java.io.IOException;
import java.util.Date;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.DATE;

public class CurrentDate extends UDF<DATE> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {

		return DATE.class;
	}

	public static DATE evaluate(Object[] args) {
		Date today = new Date();

		return new DATE(today);

	}

	public DATE exec(Data[] input) throws IOException {
		Date today = new Date();

		return new DATE(today);
	}

}
