package com.ebay.nest.arithmetic;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class MathUtil {

	public static Object plus(Object o1, Object o2) {
		if (o1 instanceof CHAR) {
			String s1 = ((CHAR) o1).getString();
			o1 = FLOAT.valueOf(s1);
		}
		if (o2 instanceof CHAR) {
			String s2 = ((CHAR) o2).getString();
			o2 = FLOAT.valueOf(s2);
		}
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			if (o1 instanceof FLOAT || o2 instanceof FLOAT) {
				double d1 = n1.doubleValue();
				double d2 = n2.doubleValue();
				return new FLOAT(d1 + d2);
			} else if (o1 instanceof DECIMAL || o2 instanceof DECIMAL) {
				BigDecimal bd1 = n1.decimalValue();
				BigDecimal bd2 = n2.decimalValue();
				try {
					BigDecimal res = bd1.add(bd2);
					return new DECIMAL(res);
				} catch (OutOfMemoryError e) {
					return null;
				}

			} else if (o1 instanceof BIGINT || o2 instanceof BIGINT) {
				long l1 = n1.longValue();
				long l2 = n2.longValue();
				return new BIGINT(l1 + l2);

			} else {
				int i1 = n1.intValue();
				int i2 = n2.intValue();
				return new INTEGER(i1 + i2);
			}

		} else if (o1 instanceof DATETIME) {
			long l = ((DATETIME) o1).getTime();
			if (o2 instanceof NUMERIC) {
				BigDecimal bd = ((NUMERIC) o2).decimalValue();
				bd = bd.multiply(new BigDecimal(24 * 60 * 60 * 1000));
				l += bd.longValue();
				if (o1 instanceof DATE) {
					Date d = new Date(l);
					return new DATE(d);
				} else if (o1 instanceof TIME) {
					Time t = new Time(l);
					return new TIME(t);
				} else if (o1 instanceof TIMESTAMP) {
					Timestamp t = new Timestamp(l);
					return new TIMESTAMP(t);
				}
			} else if (o2 instanceof INTERVAL) {
				throw new RuntimeException("Did not support INTERVAL arithmetic operations yet");
			}
		} else if (o2 instanceof DATETIME) {
			long l = ((DATETIME) o2).getTime();
			if (o1 instanceof NUMERIC) {
				BigDecimal bd = ((NUMERIC) o1).decimalValue();
				bd = bd.multiply(new BigDecimal(24 * 60 * 60 * 1000));
				l += bd.longValue();
				if (o2 instanceof DATE) {
					Date d = new Date(l);
					return new DATE(d);
				} else if (o2 instanceof TIME) {
					Time t = new Time(l);
					return new TIME(t);
				} else if (o2 instanceof TIMESTAMP) {
					Timestamp t = new Timestamp(l);
					return new TIMESTAMP(t);
				}
			} else if (o1 instanceof INTERVAL) {
				throw new RuntimeException("Did not support INTERVAL arithmetic operations yet");
			}
		}

		return null;
	}

	public static Object minus(Object o1, Object o2) {
		if (o1 instanceof CHAR) {
			String s1 = ((CHAR) o1).getString();
			o1 = FLOAT.valueOf(s1);
		}
		if (o2 instanceof CHAR) {
			String s2 = ((CHAR) o2).getString();
			o2 = FLOAT.valueOf(s2);
		}
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			if (o1 instanceof FLOAT || o2 instanceof FLOAT) {
				double d1 = n1.doubleValue();
				double d2 = n2.doubleValue();
				return new FLOAT(d1 - d2);

			} else if (o1 instanceof DECIMAL || o2 instanceof DECIMAL) {
				BigDecimal bd1 = n1.decimalValue();
				BigDecimal bd2 = n2.decimalValue();
				try {
					BigDecimal res = bd1.subtract(bd2);
					return new DECIMAL(res);
				} catch (OutOfMemoryError e) {
					return null;
				}

			} else if (o1 instanceof BIGINT || o2 instanceof BIGINT) {
				long l1 = n1.longValue();
				long l2 = n2.longValue();
				return new BIGINT(l1 - l2);

			} else {
				int i1 = n1.intValue();
				int i2 = n2.intValue();
				return new INTEGER(i1 - i2);
			}

		} else if (o1 instanceof DATETIME) {
			long l = ((DATETIME) o1).getTime();
			if (o2 instanceof NUMERIC) {
				BigDecimal bd = ((NUMERIC) o2).decimalValue();
				bd = bd.multiply(new BigDecimal(24 * 60 * 60 * 1000));
				l -= bd.longValue();
				if (o1 instanceof DATE) {
					Date d = new Date(l);
					return new DATE(d);
				} else if (o1 instanceof TIME) {
					Time t = new Time(l);
					return new TIME(t);
				} else if (o1 instanceof TIMESTAMP) {
					Timestamp t = new Timestamp(l);
					return new TIMESTAMP(t);
				}
			} else if (o2 instanceof INTERVAL) {
				throw new RuntimeException("Did not support INTERVAL arithmetic operations yet");
			}
		}

		return null;
	}

	public static Object multiply(Object o1, Object o2) {
		if (o1 instanceof CHAR) {
			String s1 = ((CHAR) o1).getString();
			o1 = FLOAT.valueOf(s1);
		}
		if (o2 instanceof CHAR) {
			String s2 = ((CHAR) o2).getString();
			o2 = FLOAT.valueOf(s2);
		}
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			if (o1 instanceof FLOAT || o2 instanceof FLOAT) {
				double d1 = n1.doubleValue();
				double d2 = n2.doubleValue();
				return new FLOAT(d1 * d2);

			} else if (o1 instanceof DECIMAL || o2 instanceof DECIMAL) {
				BigDecimal bd1 = n1.decimalValue();
				BigDecimal bd2 = n2.decimalValue();
				try {
					BigDecimal res = bd1.multiply(bd2);
					return new DECIMAL(res);
				} catch (OutOfMemoryError e) {
					return null;
				}

			} else if (o1 instanceof BIGINT || o2 instanceof BIGINT) {
				long l1 = n1.longValue();
				long l2 = n2.longValue();
				return new BIGINT(l1 * l2);

			} else {
				int i1 = n1.intValue();
				int i2 = n2.intValue();
				return new INTEGER(i1 * i2);
			}

		}

		return null;
	}

	public static Object divide(Object o1, Object o2) {
		if (o1 instanceof CHAR) {
			String s1 = ((CHAR) o1).getString();
			o1 = FLOAT.valueOf(s1);
		}
		if (o2 instanceof CHAR) {
			String s2 = ((CHAR) o2).getString();
			o2 = FLOAT.valueOf(s2);
		}
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			if (o1 instanceof FLOAT || o2 instanceof FLOAT) {
				double d1 = n1.doubleValue();
				double d2 = n2.doubleValue();
				return new FLOAT(d1 / d2);

			} else if (o1 instanceof DECIMAL || o2 instanceof DECIMAL) {
				BigDecimal bd1 = n1.decimalValue();
				BigDecimal bd2 = n2.decimalValue();
				int s1 = bd1.scale();
				int s2 = bd2.scale();
				int s = (s1 > s2) ? s1 : s2;
				try {
					BigDecimal res = bd1.divide(bd2, s, BigDecimal.ROUND_HALF_UP);
					return new DECIMAL(res);
				} catch (OutOfMemoryError e) {
					return null;
				}

			} else if (o1 instanceof BIGINT || o2 instanceof BIGINT) {
				long l1 = n1.longValue();
				long l2 = n2.longValue();
				return new BIGINT(l1 / l2);

			} else {
				int i1 = n1.intValue();
				int i2 = n2.intValue();
				return new INTEGER(i1 / i2);
			}

		}

		return null;
	}

	public static Object mod(Object o1, Object o2) {
		if (o1 instanceof CHAR) {
			String s1 = ((CHAR) o1).getString();
			o1 = FLOAT.valueOf(s1);
		}
		if (o2 instanceof CHAR) {
			String s2 = ((CHAR) o2).getString();
			o2 = FLOAT.valueOf(s2);
		}
		if (o1 == null || o2 == null) {
			return null;
		}

		if (o1 instanceof NUMERIC && o2 instanceof NUMERIC) {
			NUMERIC n1 = (NUMERIC) o1;
			NUMERIC n2 = (NUMERIC) o2;
			if (o1 instanceof FLOAT || o2 instanceof FLOAT) {
				double d1 = n1.doubleValue();
				double d2 = n2.doubleValue();
				return new FLOAT(d1 % d2);

			} else if (o1 instanceof DECIMAL || o2 instanceof DECIMAL) {
				BigDecimal bd1 = n1.decimalValue();
				BigDecimal bd2 = n2.decimalValue();
				int s1 = bd1.scale();
				int s2 = bd2.scale();
				int s = (s1 > s2) ? s1 : s2;
				try {
					BigDecimal sum = bd1.divide(bd2, s, BigDecimal.ROUND_HALF_UP);
					BigDecimal res = bd1.divide(bd2, 0, BigDecimal.ROUND_FLOOR);
					return new DECIMAL(sum.subtract(res));
				} catch (OutOfMemoryError e) {
					return null;
				}
			} else if (o1 instanceof BIGINT || o2 instanceof BIGINT) {
				long l1 = n1.longValue();
				long l2 = n2.longValue();
				return new BIGINT(l1 % l2);

			} else {
				int i1 = n1.intValue();
				int i2 = n2.intValue();
				return new INTEGER(i1 % i2);
			}
		}

		return null;
	}
}
