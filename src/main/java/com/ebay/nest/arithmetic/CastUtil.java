package com.ebay.nest.arithmetic;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ebay.nest.Data;
import com.ebay.nest.primitive.BIGINT;
import com.ebay.nest.primitive.BOOLEAN;
import com.ebay.nest.primitive.BYTE;
import com.ebay.nest.primitive.BYTEINT;
import com.ebay.nest.primitive.CHAR;
import com.ebay.nest.primitive.DATE;
import com.ebay.nest.primitive.DATETIME;
import com.ebay.nest.primitive.DECIMAL;
import com.ebay.nest.primitive.FLOAT;
import com.ebay.nest.primitive.INTEGER;
import com.ebay.nest.primitive.INTERVAL;
import com.ebay.nest.primitive.NUMERIC;
import com.ebay.nest.primitive.PERIOD;
import com.ebay.nest.primitive.SMALLINT;
import com.ebay.nest.primitive.TIME;
import com.ebay.nest.primitive.TIMESTAMP;

public class CastUtil {
	private static SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd z");

	public static Object castTo(Object o, Class klass) {
		if (klass == BYTEINT.class) {
			return CastUtil.toByteInt(o);
		} else if (klass == SMALLINT.class) {
			return CastUtil.toSmallInt(o);
		} else if (klass == INTEGER.class) {
			return CastUtil.toInteger(o);
		} else if (klass == BIGINT.class) {
			return CastUtil.toBigInt(o);
		} else if (klass == FLOAT.class) {
			return CastUtil.toFloat(o);
		} else if (klass == DECIMAL.class) {
			return CastUtil.toDecimal(o);
		} else if (klass == TIME.class) {
			return CastUtil.toTime(o);
		} else if (klass == TIMESTAMP.class) {
			return CastUtil.toTimestamp(o);
		} else if (klass == DATE.class) {
			return CastUtil.toDate(o);
		}

		return null;

	}

	public static CHAR toChar(Object o) {
		if (o == null) {
			return null;
		}

		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			String s = numeric.toString();
			return new CHAR(s);

		} else if (o instanceof BYTE) {
			byte[] bytes = ((BYTE) o).getBytes();
			String s = new String(bytes);
			return new CHAR(s);
		} else if (o instanceof CHAR) {
			return new CHAR(o.toString());
		} else if (o instanceof DATETIME) {
			return new CHAR(o.toString());
		} else if (o instanceof INTERVAL) {
			return new CHAR(o.toString());
		} else if (o instanceof PERIOD) {
			return new CHAR(o.toString());
		}

		return new CHAR(o.toString());
	}

	public static DATE toDate(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			return null;
		} else if (o instanceof CHAR ) {
			String s = ((CHAR) o).getString();
			DATE d = DATE.valueOf(s);
			return d;
		} else if (o instanceof BYTE) {
			byte[] bytes = ((BYTE) o).getBytes();
			String s = new String(bytes);
			DATE d = DATE.valueOf(s);
			return d;
		} else if (o instanceof DATETIME) {
			DATETIME datetime = (DATETIME) o;
			long time = datetime.getTime();
			Date d = new Date(time);
			String str=defaultFormat.format(d);
			try {
				d=defaultFormat.parse(str);
			} catch (ParseException e) {
				//never happened!
			}
			return new DATE(d);
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}
		return null;
	}

	public static TIMESTAMP toTimestamp(Object o) {
		if (o == null) {
			return null;
		}
		else if (o instanceof NUMERIC) {
			return null;
		} else if (o instanceof CHAR) {
			String s = ((CHAR) o).getString();
			TIMESTAMP d = TIMESTAMP.valueOf(s);
			return d;
		} else if (o instanceof BYTE) {
			byte[] bytes = ((BYTE) o).getBytes();
			String s = new String(bytes);
			TIMESTAMP d = TIMESTAMP.valueOf(s);
			return d;
		} else if (o instanceof DATETIME) {
			DATETIME datetime = (DATETIME) o;
			long time = datetime.getTime();
			Timestamp d = new Timestamp(time);
			return new TIMESTAMP(d);
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}
		return null;
	}

	public static TIME toTime(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof NUMERIC) {
			return null;
		} else if (o instanceof CHAR) {
			String s = ((CHAR) o).getString();
			TIME d = TIME.valueOf(s);
			return d;
		} else if (o instanceof BYTE) {
			byte[] bytes = ((BYTE) o).getBytes();
			String s = new String(bytes);
			TIME d = TIME.valueOf(s);
			return d;
		} else if (o instanceof DATETIME) {
			DATETIME datetime = (DATETIME) o;
			long time = datetime.getTime();
			return new TIME(new Time(time));
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}
		return null;
	}

	public static BYTEINT toByteInt(Object o) {
		if (o == null) {
			return null;
		}

		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			byte b = numeric.byteValue();
			return new BYTEINT(b);

		} else if (o instanceof CHAR) {
			try {
				char[] chars = ((CHAR) o).getChars();

				byte b = Byte.parseByte(new String(chars));
				return new BYTEINT(b);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static SMALLINT toSmallInt(Object o) {
		if (o == null) {
			return null;
		}

		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			short s = numeric.shortValue();
			return new SMALLINT(s);

		} else if (o instanceof CHAR) {
			try {
				char[] chars = ((CHAR) o).getChars();

				short s = Short.parseShort(new String(chars));
				return new SMALLINT(s);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static INTEGER toInteger(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			int i = numeric.intValue();
			return new INTEGER(i);

		} else if (o instanceof CHAR) {
			try {
				char[] chars = ((CHAR) o).getChars();
				int i = Integer.parseInt(new String(chars));
				return new INTEGER(i);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static BIGINT toBigInt(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			long l = numeric.longValue();
			return new BIGINT(l);

		} else if (o instanceof CHAR) {
			try {
				String s = ((CHAR) o).getString();
				long l = Long.parseLong(s);
				return new BIGINT(l);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static FLOAT toFloat(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			double l = numeric.doubleValue();
			return new FLOAT(l);

		} else if (o instanceof CHAR) {
			try {
				String s = ((CHAR) o).getString();
				double l = Double.parseDouble(s);
				return new FLOAT(l);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static DECIMAL toDecimal(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			BigDecimal l = numeric.decimalValue();
			return new DECIMAL(l);

		} else if (o instanceof CHAR) {
			try {
				String s = ((CHAR) o).getString();
				BigDecimal bd = new BigDecimal(s);
				return new DECIMAL(bd);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static DECIMAL toDecimal(Object o, int pre, int scale) {
		if (o == null) {
			return null;
		}
		if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			BigDecimal bd = numeric.decimalValue();
			bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
			return new DECIMAL(bd);

		} else if (o instanceof CHAR) {
			try {
				String s = ((CHAR) o).getString();
				BigDecimal bd = new BigDecimal(s);
				bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
				return new DECIMAL(bd);
			} catch (NumberFormatException e) {
				return null;
			}

		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;
	}

	public static BYTE toByte(Object o) {
		if (o == null) {

			return null;
		}
		if (o instanceof NUMERIC) {
			return null;

		} else if (o instanceof CHAR) {

			String s = ((CHAR) o).getString();
			byte[] bytes = s.getBytes();
			return new BYTE(bytes);

		} else if (o instanceof BYTE) {
			byte[] bytes = ((BYTE) o).getBytes();
			return new BYTE(bytes);
		} else if (o instanceof DATETIME) {
			return null;
		} else if (o instanceof INTERVAL) {
			return null;
		} else if (o instanceof PERIOD) {
			return null;
		}

		return null;

	}

	public static BOOLEAN toBoolean(Data o) {
		// please be aware, do not ever return null
		if (o == null) {
			return BOOLEAN.FALSE;
		}
		if (o instanceof BOOLEAN) {
			return (BOOLEAN) o;
		} else if (o instanceof NUMERIC) {
			NUMERIC numeric = (NUMERIC) o;
			BigDecimal bd = numeric.decimalValue();
			if (bd.compareTo(BigDecimal.ZERO) == 0) {
				return BOOLEAN.FALSE;
			}
			return BOOLEAN.TRUE;
		} else if (o instanceof CHAR) {
			String s = ((CHAR) o).getString();
			if ("".equals(s.trim())) {
				return BOOLEAN.FALSE;
			}
			return BOOLEAN.TRUE;
		} else if (o instanceof BYTE) {
			byte[] bs = ((BYTE) o).getBytes();
			if (bs.length == 0) {
				return BOOLEAN.FALSE;
			}
			return BOOLEAN.TRUE;
		} else if (o instanceof DATETIME) {
			return BOOLEAN.TRUE;
		} else if (o instanceof INTERVAL) {
			throw new RuntimeException("Did not support INTERVAL arithmetic operations yet");
		} else if (o instanceof PERIOD) {
			throw new RuntimeException("Did not support INTERVAL arithmetic operations yet");
		}
		return BOOLEAN.FALSE;

	}
}
