package com.ebay.nest.udf;

import java.io.IOException;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CompatibleUtil;
import com.ebay.nest.primitive.BOOLEAN;

public class When extends UDF<Data> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length < 2) {
			throw new SemanticException("CaseWhen have wrong parameter lengths");
		}

		Class returnClass = params[params.length - 1];

		for (int i = 0; i < params.length - 1; i += 2) {

			Class whenClass = params[i];
			Class thenClass = params[i + 1];
			if (whenClass != Boolean.class) {
				throw new SemanticException("When Statement at CaseWhen should be the same type");
			}

			returnClass = CompatibleUtil.getCompatiableForType(returnClass, thenClass);
			if (returnClass == null) {
				throw new SemanticException("Then Statement at CaseWhen should be compatible");
			}

		}

		return returnClass;

	}

	public static Object evaluate(Object[] args) {
		int argnum = args.length;
		int i;

		for (i = 0; i < argnum - 1; i += 2) {
			Object when = args[i];
			Object then = args[i + 1];
			if (when == null || !(when instanceof Boolean) || (Boolean) when == false) {
				continue;
			}
			return then;
		}
		if (i == argnum) {
			return null;
		}
		return args[argnum - 1];

	}

	public Data exec(Data[] input) throws IOException {
		int argnum = input.length;
		int i;

		for (i = 0; i < argnum - 1; i += 2) {
			Data when = input[i];
			Data then = input[i + 1];
			if (when == null || !(when instanceof BOOLEAN) || (BOOLEAN) when == BOOLEAN.FALSE) {
				continue;
			}
			return then;
		}
		if (i == argnum) {
			return null;
		}
		return input[argnum - 1];

	}

}
