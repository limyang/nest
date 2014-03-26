package com.ebay.nest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class MSCTest {

	private static final int[] multiplier = new int[] { 1000, 100, 10, 1 };
	public static SimpleDateFormat defaultformat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException {

		SimpleDateFormat defaultformat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			defaultformat.parse("2010-10-23");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String testStepContent = FileUtils.readFileToString(new File("/Users/limyang/dev/etl/SSAProductFactMigration/src/test/java/com/ebay/ddi/seller/migration/driven/test_step.yml"));
		


	}

	public static Double round(Double d, Integer round) {
		if (round == null) {
			return d;
		}

		double t = Math.pow(10, round);
		double times = d * t + 1e-10;
		double times_1 = t * 10 * d + 1e-9;

		if (((long) times) % 2 != 0 || times_1 - (long) times_1 > 2e-9) {
			return ((long) (times + 0.5)) / t;
		} else {
			return ((long) (times + 0.4)) / t;
		}

	}

	public String replacestring(String b) {

		if (b == null) {
			return null;
		}
		boolean wasDollor = false;
		StringBuilder db = new StringBuilder(b.length());

		Character enclosure = null;
		StringBuilder sb = new StringBuilder(b.length());
		for (int i = 0; i < b.length(); i++) {

			char currentChar = b.charAt(i);
			if (enclosure == null) {
				if (currentChar == '\'' || b.charAt(i) == '\"') {
					enclosure = currentChar;
				}
				continue;
			}
			if (currentChar == '$') {
				int j = i;
				j++;
				if (b.charAt(j) == '{') {
					wasDollor = true;
					i = j;
					continue;
				}
			}
			if (currentChar == '}' && wasDollor) {
				String dolString = db.toString().trim();
				Properties properties = System.getProperties();
				Set keys = properties.keySet();
				if (keys.contains(dolString)) {

					dolString = properties.getProperty(dolString);
				}

				sb.append(dolString);
				db = new StringBuilder(b.length());
				wasDollor = false;
				continue;
			}

			if (enclosure.equals(currentChar)) {
				enclosure = null;
				continue;
			}
			if (currentChar == '\\' && (i + 6 < b.length()) && b.charAt(i + 1) == 'u') {
				int code = 0;
				int base = i + 2;
				for (int j = 0; j < 4; j++) {
					int digit = Character.digit(b.charAt(j + base), 16);
					code += digit * multiplier[j];
				}
				if (wasDollor) {
					db.append((char) code);
				} else

					sb.append((char) code);
				i += 5;
				continue;
			}
			if (currentChar == '\\' && (i + 4 < b.length())) {
				char i1 = b.charAt(i + 1);
				char i2 = b.charAt(i + 2);
				char i3 = b.charAt(i + 3);
				if ((i1 >= '0' && i1 <= '1') && (i2 >= '0' && i2 <= '7') && (i3 >= '0' && i3 <= '7')) {
					byte bVal = (byte) ((i3 - '0') + ((i2 - '0') * 8) + ((i1 - '0') * 8 * 8));
					byte[] bValArr = new byte[1];
					bValArr[0] = bVal;
					String tmp = new String(bValArr);
					if (wasDollor) {
						db.append(tmp);
					} else

						sb.append(tmp);

					i += 3;
					continue;
				}
			}
			if (currentChar == '\\' && (i + 2 < b.length())) {
				char n = b.charAt(i + 1);
				String ap;
				switch (n) {
				case '0':
					ap = "\0";
					break;
				case '\'':
					ap = "'";
					break;
				case '"':
					ap = "\"";
					break;
				case 'b':
					ap = "\b";
					break;
				case 'n':
					ap = "\n";
					break;
				case 'r':
					ap = "\r";
					break;
				case 't':
					ap = "\t";
					break;
				case 'Z':
					ap = "\u001A";
					break;
				case '\\':
					ap = "\\";
					break;
				case '%':
					ap = "\\%";
					break;
				case '_':
					ap = "\\_";
					break;
				default:
					ap = "" + n;
				}
				if (wasDollor) {
					db.append(ap);
				} else

					sb.append(ap);
				i++;
			} else {
				if (wasDollor) {
					db.append(currentChar);
				} else

					sb.append(currentChar);

				// sb.append(currentChar);
			}
		}
		return sb.toString();

	}

}
