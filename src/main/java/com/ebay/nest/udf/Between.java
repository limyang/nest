package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CompareUtil;
import com.ebay.nest.primitive.BOOLEAN;

public class Between extends UDF<BOOLEAN> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {

		return Boolean.class;
	}

	public static Boolean evaluate(Object[] args) {

		Object com = args[0];
		if (com == null ) {
			return false;
		}
		Object max = args[2];
		Object min = args[1];
		if (max == null  || min == null ) {
			return false;
		}
		Integer res1 = CompareUtil.compareTo(max, com);
		Integer res2 = CompareUtil.compareTo(com, min);
		if (res1 == null || res1 == null) {
			return false;
		}
		if (res1 >= 0 && res2 >= 0) {
			return true;
		}
		return false;
	}

	public BOOLEAN exec(Data[] input) throws IOException {
		Object com = input[0];
		if (com == null) {
			return BOOLEAN.FALSE;
		}
		Object max = input[2];
		Object min = input[1];
		if (max == null || min == null) {
			return BOOLEAN.FALSE;
		}
		Integer res1 = CompareUtil.compareTo(max, com);
		Integer res2 = CompareUtil.compareTo(com, min);
		if (res1 == null || res1 == null) {
			return BOOLEAN.FALSE;
		}
		if (res1 >= 0 && res2 >= 0) {
			return BOOLEAN.TRUE;
		}
		return BOOLEAN.FALSE;
	}

}
