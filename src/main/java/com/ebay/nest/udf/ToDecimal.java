package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.INTEGER;

public class ToDecimal extends UDF<DECIMAL> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 1 && params.length != 3) {
			throw new SemanticException("Cast Function (Decimal) did not have correct parameters");
		}
		if (params.length == 3 && params[0] != INTEGER.class && params[1] != INTEGER.class) {
			throw new SemanticException("Cast Function (Decimal) did not have correct parameter types");
		}

		return DECIMAL.class;
	}

	public static DECIMAL evaluate(Object[] args) {
		if (args.length == 3) {
			Object o1 = args[0];
			Object o2 = args[1];
			Object o3 = args[2];
			if (o3 == null) {
				return null;
			}
			int pre = ((INTEGER) o1).intValue();
			int scale = ((INTEGER) o2).intValue();
			return CastUtil.toDecimal(o3, pre, scale);
		}

		Object o1 = args[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toDecimal(o1);

	}

	public DECIMAL exec(Data[] input) throws IOException {
		if (input.length == 3) {
			Data o1 = input[0];
			Data o2 = input[1];
			Data o3 = input[2];
			if (o3 == null) {
				return null;
			}
			int pre = ((INTEGER) o1).intValue();
			int scale = ((INTEGER) o2).intValue();
			return CastUtil.toDecimal(o3, pre, scale);
		}

		Data o1 = input[0];
		if (o1 == null) {
			return null;
		}
		return CastUtil.toDecimal(o1);
	}

}
