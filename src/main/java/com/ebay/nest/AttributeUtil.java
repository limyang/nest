package com.ebay.nest;


import com.ebay.nest.arithmetic.CastUtil;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.PERIOD;
import com.ebay.nest.primitive.SMALLINT;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class AttributeUtil {

	public static Object castTo(TypeInfo attr, Object o) {
		if (attr instanceof BYTEINT.Attribute) {
			return CastUtil.toByteInt(o);
		} else if (attr instanceof SMALLINT.Attribute) {
			return CastUtil.toSmallInt(o);
		} else if (attr instanceof INTEGER.Attribute) {
			return CastUtil.toInteger(o);
		} else if (attr instanceof BIGINT.Attribute) {
			return CastUtil.toBigInt(o);
		} else if (attr instanceof DECIMAL.Attribute) {
			return CastUtil.toDecimal(o);
		} else if (attr instanceof FLOAT.Attribute) {
			return CastUtil.toFloat(o);
		} else if (attr instanceof CHAR.Attribute) {
			return CastUtil.toChar(o);
		} else if (attr instanceof BYTE.Attribute) {
			return CastUtil.toByte(o);
		} else if (attr instanceof TIME.Attribute) {
			return CastUtil.toTime(o);
		} else if (attr instanceof TIMESTAMP.Attribute) {
			return CastUtil.toTimestamp(o);
		} else if (attr instanceof DATE.Attribute) {
			return CastUtil.toDate(o);
		} else if (attr instanceof INTERVAL.Attribute) {
			return null;
		} else if (attr instanceof PERIOD.Attribute) {
			return null;
		}
		return null;

	}
}
