package com.ebay.nest.utils;

import java.util.Properties;
import java.util.Set;

public class StringUtil extends in.masr.utils.StringU {

	public static String unescapeIdentifier(String val) {
		if (val == null) {
			return null;
		}
		if (val.charAt(0) == '`' && val.charAt(val.length() - 1) == '`') {
			val = val.substring(1, val.length() - 1);
		}
		return val;
	}

	private static final int[] multiplier = new int[] { 1000, 100, 10, 1 };

	public static String unescapeString(String b) {
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

			}
		}
		return sb.toString();
	}

}
