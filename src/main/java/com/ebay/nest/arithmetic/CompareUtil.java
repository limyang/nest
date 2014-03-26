package com.ebay.nest.arithmetic;

import java.math.BigDecimal;

import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;

import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class CompareUtil {

	public static Boolean great(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		Integer i = compareTo(o1, o2);
		if (i != null && i > 0) {
			return true;
		}

		return false;

	}

	public static Boolean greatEqual(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}

		Integer i = compareTo(o1, o2);
		if (i != null && i >= 0) {
			return true;
		}

		return false;
	}

	public static Boolean less(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}

		Integer i = compareTo(o1, o2);
		if (i != null && i < 0) {
			return true;
		}

		return false;
	}

	public static Boolean lessEqual(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}

		Integer i = compareTo(o1, o2);
		if (i != null && i <= 0) {
			return true;
		}

		return false;
	}

	public static Boolean equal(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}

		Integer i = compareTo(o1, o2);
		if (i != null && i == 0) {
			return true;
		}

		return false;
	}

	public static Boolean notEqual(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}

		Integer i = compareTo(o1, o2);
		if (i != null && i != 0) {
			return true;
		}

		return false;
	}

	public static Integer compareTo(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			BigDecimal bd1 = n1.decimalValue();
			BigDecimal bd2 = n2.decimalValue();
			return bd1.compareTo(bd2);
		} else if (o1 instanceof NUMERIC || o2 instanceof NUMERIC) {
			if (o2 instanceof CHAR) {
				String s2 = ((CHAR) o2).getString();
				BigDecimal bd2;
				try {
					bd2 = new BigDecimal(s2);
				} catch (NumberFormatException e) {
					return null;
				}
				NUMERIC n1 = (NUMERIC) o1;
				BigDecimal bd1 = n1.decimalValue();
				return bd1.compareTo(bd2);

			}

			if (o1 instanceof CHAR) {
				String s1 = ((CHAR) o1).getString();
				BigDecimal bd1;
				try {
					bd1 = new BigDecimal(s1);
				} catch (NumberFormatException e) {
					return null;
				}
				NUMERIC n2 = (NUMERIC) o2;
				BigDecimal bd2 = n2.decimalValue();
				return bd1.compareTo(bd2);
			}

		} else if (o1 instanceof DATETIME || o2 instanceof DATETIME) {
			if (o1 instanceof CHAR) {
				String s1 = ((CHAR) o1).getString();
				if (o2 instanceof TIMESTAMP) {
					o1 = TIMESTAMP.valueOf(s1);
				} else if (o2 instanceof DATE) {
					o1 = DATE.valueOf(s1);
				} else if (o2 instanceof TIME) {
					o1 = TIME.valueOf(s1);
				} else {
					return null;
				}
			}

			if (o2 instanceof CHAR) {
				String s2 = ((CHAR) o2).getString();
				if (o1 instanceof TIMESTAMP) {
					o2 = TIMESTAMP.valueOf(s2);
				} else if (o1 instanceof DATE) {
					o2 = DATE.valueOf(s2);
				} else if (o2 instanceof TIME) {
					o2 = TIME.valueOf(s2);
				} else {
					return null;
				}
			}

			DATETIME d1 = (DATETIME) o1;
			DATETIME d2 = (DATETIME) o2;
			return d1.compareTo(d2);

		} else if (o1 instanceof CHAR && o2 instanceof CHAR) {
			CHAR c1 = (CHAR) o1;
			CHAR c2 = (CHAR) o2;
			return c1.compareTo(c2);

		} else if (o1 instanceof BYTE && o2 instanceof BYTE) {
			BYTE b1 = (BYTE) o1;
			BYTE b2 = (BYTE) o2;
			return b1.compareTo(b2);

		} else if (o1 instanceof INTERVAL && o2 instanceof INTERVAL) {
			INTERVAL i1 = (INTERVAL) o1;
			INTERVAL i2 = (INTERVAL) o2;
			return i1.compareTo(i2);
		}

		return null;
	}

}
