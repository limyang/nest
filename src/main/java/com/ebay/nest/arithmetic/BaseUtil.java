package com.ebay.nest.arithmetic;

import java.math.BigDecimal;

import com.ebay.nest.Data;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.INTEGER;

public class BaseUtil {

	public static DECIMAL DecimalLiteral(String s) {
		if (s == null) {
			return null;
		}
		try {
			BigDecimal bd = new BigDecimal(s);
			return new DECIMAL(bd);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static CHAR CharLiteral(String s) {
		if (s == null) {
			return null;
		}
		return new CHAR(s);
	}

	public static INTEGER IntegerLiteral(String s) {
		if (s == null) {
			return null;
		}
		return INTEGER.valueOf(s);

	}

	public static BIGINT BigIntLiteral(String s) {
		if (s == null) {
			return null;
		}
		return BIGINT.valueOf(s);

	}

	public static DATE DateLiteral(String s) {
		if (s == null) {
			return null;
		}
		return DATE.valueOf(s);
	}

	public static Boolean True() {
		return true;
	}

	public static Boolean False() {
		return false;
	}

	public static Data Null() {
		return null;
	}

}
