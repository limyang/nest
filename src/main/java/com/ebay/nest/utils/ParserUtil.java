package com.ebay.nest.utils;

import static com.ebay.nest.parser.SQLParser.ByteLengthLiteral;
import static com.ebay.nest.parser.SQLParser.DecimalLiteral;
import static com.ebay.nest.parser.SQLParser.Digit;
import static com.ebay.nest.parser.SQLParser.Exponent;
import static com.ebay.nest.parser.SQLParser.HexDigit;
import static com.ebay.nest.parser.SQLParser.Identifier;
import static com.ebay.nest.parser.SQLParser.Letter;
import static com.ebay.nest.parser.SQLParser.Number;
import static com.ebay.nest.parser.SQLParser.RegexComponent;
import static com.ebay.nest.parser.SQLParser.StringLiteral;

import org.apache.commons.lang.ArrayUtils;

public class ParserUtil {

	private static int[] leafTypes = { Letter, HexDigit, Digit, Exponent, RegexComponent, StringLiteral,
			DecimalLiteral, ByteLengthLiteral, Number, Identifier };

	public static int[] getLeafTypes() {
		return leafTypes;
	}

	public static boolean isLeafType(int type) {
		return ArrayUtils.contains(leafTypes, type);
	}
}
