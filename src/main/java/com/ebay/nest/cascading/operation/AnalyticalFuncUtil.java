package com.ebay.nest.cascading.operation;

import java.math.BigDecimal;

import cascading.tuple.Tuple;

import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.INTEGER;

public class AnalyticalFuncUtil {

	public static Object computerAvg(Tuple[][] tuples, int first, int last, int j, Class[] innerClass, Class returnClass) {

		BigDecimal sum = BigDecimal.ZERO;
		Integer count = 0;
		for (int i = first; i <= last; i++) {
			Tuple t = tuples[i][j];
			BigDecimal e = CastUtil.toDecimal(t.getObject(0)).decimalValue();
			sum = sum.add(e);
			count++;
		}
		sum = sum.divide(new BigDecimal(count), sum.scale(), BigDecimal.ROUND_HALF_UP);
		DECIMAL res = new DECIMAL(sum);
		return CastUtil.castTo(res, returnClass);

	}

	public static Object computerSum(Tuple[][] tuples, int first, int last, int j, Class[] innerClass, Class returnClass) {
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = first; i <= last; i++) {
			Tuple t = tuples[i][j];
			BigDecimal e = CastUtil.toDecimal(t.getObject(0)).decimalValue();
			if (e == null) {
				continue;
			}
			sum = sum.add(e);

		}
		DECIMAL res = new DECIMAL(sum);

		return CastUtil.castTo(res, returnClass);
	}

	public static Object computerMax(Tuple[][] tuples, int first, int last, int j, Class[] innerClass, Class returnClass) {
		BigDecimal max = BigDecimal.ZERO;
		for (int i = first; i <= last; i++) {
			Tuple t = tuples[i][j];
			BigDecimal e = CastUtil.toDecimal(t.getObject(0)).decimalValue();
			if (max.compareTo(e) < 0) {
				max = e;
			}
		}
		DECIMAL res = new DECIMAL(max);

		return CastUtil.castTo(res, returnClass);
	}

	public static Object countForRow(Tuple[][] tuples, int[] map, int line, int j, Class[] classes, Class class1) {

		return new INTEGER(map[line]);

	}
}
