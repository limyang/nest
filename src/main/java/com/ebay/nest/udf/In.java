package com.ebay.nest.udf;

import java.io.IOException;
import java.math.BigDecimal;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.primitive.BOOLEAN;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;

public class In extends UDF<BOOLEAN> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {

		return Boolean.class;
	}

	public static Boolean evaluate(Object[] args) {
		int argnum = args.length;
		if (argnum < 2) {
			return false;
		}
		Object in = args[0];
		if (in == null) {
			return false;
		}
		if (in instanceof NUMERIC) {
			for (int i = 1; i < argnum; i++) {
				Object arg = args[i];
				if (arg == null || !(arg instanceof NUMERIC)) {
					continue;
				}
				BigDecimal inb = ((NUMERIC) in).decimalValue();
				BigDecimal argb = ((NUMERIC) arg).decimalValue();
				if (inb.compareTo(argb) == 0) {
					return true;
				}

			}

		} else if (in instanceof CHAR) {
			for (int i = 1; i < argnum; i++) {
				Object arg = args[i];
				if (arg == null || !(arg instanceof CHAR)) {
					continue;
				}
				if (((CHAR) in).compareTo((CHAR) arg) == 0) {
					return true;
				}
			}
		} else if (in instanceof BYTE) {

		} else if (in instanceof INTERVAL) {

		} else if (in instanceof DATETIME) {
			for (int i = 1; i < argnum; i++) {
				Object arg = args[i];
				if (arg == null || !(arg instanceof DATETIME)) {
					continue;
				}
				if (((DATETIME) in).compareTo((DATETIME) arg) == 0) {
					return true;
				}
			}
		}

		return false;
	}

	public BOOLEAN exec(Data[] input) throws IOException {
		int argnum = input.length;
		if (argnum < 2) {
			return BOOLEAN.FALSE;
		}
		Object in = input[0];
		if (in == null) {
			return BOOLEAN.FALSE;
		}
		if (in instanceof NUMERIC) {
			for (int i = 1; i < argnum; i++) {
				Object arg = input[i];
				if (arg == null || !(arg instanceof NUMERIC)) {
					continue;
				}
				BigDecimal inb = ((NUMERIC) in).decimalValue();
				BigDecimal argb = ((NUMERIC) arg).decimalValue();
				if (inb.compareTo(argb) == 0) {
					return BOOLEAN.TRUE;
				}

			}

		} else if (in instanceof CHAR) {
			for (int i = 1; i < argnum; i++) {
				Object arg = input[i];
				if (arg == null || !(arg instanceof CHAR)) {
					continue;
				}
				if (((CHAR) in).compareTo((CHAR) arg) == 0) {
					return BOOLEAN.TRUE;
				}
			}
		} else if (in instanceof BYTE) {

		} else if (in instanceof INTERVAL) {

		} else if (in instanceof DATETIME) {
			for (int i = 1; i < argnum; i++) {
				Object arg = input[i];
				if (arg == null || !(arg instanceof DATETIME)) {
					continue;
				}
				if (((DATETIME) in).compareTo((DATETIME) arg) == 0) {
					return BOOLEAN.TRUE;
				}
			}
		}

		return BOOLEAN.FALSE;
	}

}
