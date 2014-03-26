package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.INTEGER;

public class Index extends UDF<INTEGER> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 2) {
			throw new SemanticException("Function(Index) should have two parameters");
		}
		if (!CHAR.class.isAssignableFrom(params[0]) && !CHAR.class.isAssignableFrom(params[1])) {

			throw new SemanticException("Function(Index) should have correnct types");
		}

		return INTEGER.class;
	}

	public static INTEGER evaluate(Object[] args) {
		Object arg0 = args[0];
		Object arg1 = args[1];
		if (arg0 == null  || arg1 == null) {
			return null;
		}
		CHAR chars = (CHAR) arg1;
		String s, m;
		if (chars.getAttribute().isCaseSensitive()) {
			s = ((CHAR) arg1).getString();
			m = ((CHAR) arg0).getString();

		} else {
			s = ((CHAR) arg1).getString().toLowerCase();
			m = ((CHAR) arg0).getString().toLowerCase();
		}

		int res = m.indexOf(s);
		res += 1;

		return new INTEGER(res);

	}

	public INTEGER exec(Data[] input) throws IOException {
		Data arg0 = input[0];
		Data arg1 = input[1];
		if (arg0 == null || arg1 == null) {
			return null;
		}
		CHAR chars = (CHAR) arg1;
		String s, m;
		if (chars.getAttribute().isCaseSensitive()) {
			s = ((CHAR) arg1).getString();
			m = ((CHAR) arg0).getString();

		} else {
			s = ((CHAR) arg1).getString().toLowerCase();
			m = ((CHAR) arg0).getString().toLowerCase();
		}

		int res = m.indexOf(s);
		res += 1;

		return new INTEGER(res);
	}

}
