package com.ebay.nest.udf;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import com.ebay.nest.Data;
import com.ebay.nest.UDF;
import com.ebay.nest.analyzer.SemanticException;
import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.INTEGER;

public class AddMonths extends UDF<DATE> {

	private static final long serialVersionUID = 1L;

	public Class valideParameterType(Class[] params) throws SemanticException {
		if (params.length != 2) {
			throw new SemanticException("Function(add_months) should have two parameters");
		}

		return DATE.class;
	}

	public static DATE evaluate(Object[] args) {
		Object arg0 = args[0];
		Object arg1 = args[1];
		if (arg0 == null || arg1 == null) {
			return null;
		}
		DATE date = CastUtil.toDate(arg0);
		INTEGER months = CastUtil.toInteger(arg1);
		if (date == null || months == null) {
			return null;
		}

		Date d = new Date(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, months.intValue());
		return new DATE(cal.getTime().getTime());

	}

	public DATE exec(Data[] input) throws IOException {
		Data arg0 = input[0];
		Data arg1 = input[1];
		if (arg0 == null || arg1 == null) {
			return null;
		}
		DATE date = CastUtil.toDate(arg0);
		INTEGER months = CastUtil.toInteger(arg1);
		if (date == null || months == null) {
			return null;
		}

		Date d = new Date(date.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, months.intValue());
		return new DATE(cal.getTime().getTime());
	}

}
