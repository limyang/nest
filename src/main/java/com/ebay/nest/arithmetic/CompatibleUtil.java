package com.ebay.nest.arithmetic;

import com.ebay.nest.Data;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;
import com.ebay.nest.primitive.PERIOD;

public class CompatibleUtil {

	public static Class getCompatiableForType(Class a, Class b) {
		if (a == Data.class) {
			return b;
		}
		if (b == Data.class) {
			return a;
		}
		if (NUMERIC.class.isAssignableFrom(a)) {
			if (!NUMERIC.class.isAssignableFrom(b)) {
				return null;
			}
			return NUMERIC.class;
		}
		if (CHAR.class.isAssignableFrom(a)) {
			if (!CHAR.class.isAssignableFrom(b)) {
				return null;
			}
			return CHAR.class;
		}
		if (BYTE.class.isAssignableFrom(a)) {
			if (!BYTE.class.isAssignableFrom(b)) {
				return null;
			}
			return BYTE.class;
		}
		if (DATETIME.class.isAssignableFrom(a)) {
			if (!DATETIME.class.isAssignableFrom(b)) {
				return null;
			}
			return DATETIME.class;
		}
		if (PERIOD.class.isAssignableFrom(a)) {
			if (!PERIOD.class.isAssignableFrom(b)) {
				return null;
			}

			return PERIOD.class;
		}
		if (INTERVAL.class.isAssignableFrom(a)) {
			if (!INTERVAL.class.isAssignableFrom(b)) {
				return null;
			}
			return INTERVAL.class;
		}

		return null;
	}

}
