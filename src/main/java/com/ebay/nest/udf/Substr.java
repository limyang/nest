package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.CHAR;

public class Substr extends UDF<CHAR> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length < 2 || params.length > 3) {
			throw new SemanticException("Function Substr did have have correct types number");
		}
		if (params[0] != CHAR.class) {
			throw new SemanticException("Function Substr parameter should be char type");
		}

		return CHAR.class;
	}

	public static CHAR evaluate(Object[] args) {
		// First, verify if have null object, Very Important
		Object str = args[0];
		if (str == null) {
			return null;
		}
		String s = str.toString();

		if (args.length == 2) {
			Object param1 = args[1];
			int b;
			if (param1 == null) {
				return null;
			} else {
				b = Integer.parseInt(args[1].toString());
			}
			b = b - 1;
			if (b < 0) {
				b = 0;
			}
			s = s.substring(b);
		} else if (args.length == 3) {
			Object param1 = args[1];
			int b, e;
			if (param1 == null) {
				return null;
			} else {
				b = Integer.parseInt(args[1].toString()) - 1;
			}
			if (b < 0) {
				b = 0;
			}
			Object param2 = args[2];
			if (param2 == null) {
				return null;
			} else {
				e = Integer.parseInt(args[2].toString());
			}
			e = b + e;
			if (e > s.length() - 1) {
				e = s.length() - 1;
			}

			s = s.substring(b, e);
		}
		return new CHAR(s);

	}

	public CHAR exec(Data[] input) throws IOException {
		// First, verify if have null object, Very Important
		Data str = input[0];
		if (str == null) {
			return null;
		}
		String s = str.toString();

		if (input.length == 2) {
			Data param1 = input[1];
			int b;
			if (param1 == null) {
				return null;
			} else {
				b = Integer.parseInt(input[1].toString());
			}
			b = b - 1;
			if (b < 0) {
				b = 0;
			}
			s = s.substring(b);
		} else if (input.length == 3) {
			Object param1 = input[1];
			int b, e;
			if (param1 == null) {
				return null;
			} else {
				b = Integer.parseInt(input[1].toString()) - 1;
			}
			if (b < 0) {
				b = 0;
			}
			Object param2 = input[2];
			if (param2 == null) {
				return null;
			} else {
				e = Integer.parseInt(input[2].toString());
			}
			e = b + e;
			if (e > s.length() - 1) {
				e = s.length() - 1;
			}

			s = s.substring(b, e);
		}
		return new CHAR(s);
	}

}
