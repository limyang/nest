package com.ebay.nest.utils.format;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import sun.util.calendar.CalendarUtils;
import sun.util.calendar.ZoneInfoFile;
import sun.util.resources.LocaleData;

public class SQLDateFormat extends DateFormat {

	static final long serialVersionUID = 4774881970558875024L;

	static final int currentSerialVersion = 1;

	private int serialVersionOnStream = currentSerialVersion;

	private String pattern;

	transient private NumberFormat originalNumberFormat;
	transient private String originalNumberPattern;

	transient private char minusSign = '-';

	transient private boolean hasFollowingMinusSign = false;

	transient private char[] compiledPattern;

	private final static int TAG_QUOTE_ASCII_CHAR = 100;
	private final static int TAG_QUOTE_CHARS = 101;

	transient private char zeroDigit;

	private DateFormatSymbols formatData;

	private Date defaultCenturyStart;

	transient private int defaultCenturyStartYear;

	private static final int MILLIS_PER_MINUTE = 60 * 1000;

	private static final String GMT = "GMT";

	private static final ConcurrentMap<String, String[]> cachedLocaleData = new ConcurrentHashMap<String, String[]>(3);

	private static final ConcurrentMap<Locale, NumberFormat> cachedNumberFormatData = new ConcurrentHashMap<Locale, NumberFormat>(
			3);

	private Locale locale;

	transient boolean useDateFormatSymbols;

	public SQLDateFormat() {
		this(SHORT, SHORT, Locale.getDefault());
	}

	public SQLDateFormat(String pattern) {
		this(pattern, Locale.getDefault());
	}

	public SQLDateFormat(String pattern, Locale locale) {
		if (pattern == null || locale == null) {
			throw new NullPointerException();
		}

		initializeCalendar(locale);
		this.pattern = pattern;
		this.formatData = DateFormatSymbols.getInstanceRef(locale);
		this.locale = locale;
		initialize(locale);
	}

	public SQLDateFormat(String pattern, DateFormatSymbols formatSymbols) {
		if (pattern == null || formatSymbols == null) {
			throw new NullPointerException();
		}

		this.pattern = pattern;
		this.formatData = (DateFormatSymbols) formatSymbols.clone();
		this.locale = Locale.getDefault();
		initializeCalendar(this.locale);
		initialize(this.locale);
		useDateFormatSymbols = true;
	}

	SQLDateFormat(int timeStyle, int dateStyle, Locale loc) {
		if (loc == null) {
			throw new NullPointerException();
		}

		this.locale = loc;
		// initialize calendar and related fields
		initializeCalendar(loc);

		/* try the cache first */
		String key = getKey();
		String[] dateTimePatterns = cachedLocaleData.get(key);
		if (dateTimePatterns == null) { /* cache miss */
			ResourceBundle r = LocaleData.getDateFormatData(loc);
			if (!isGregorianCalendar()) {
				try {
					dateTimePatterns = r.getStringArray(getCalendarName() + ".DateTimePatterns");
				} catch (MissingResourceException e) {
				}
			}
			if (dateTimePatterns == null) {
				dateTimePatterns = r.getStringArray("DateTimePatterns");
			}
			/* update cache */
			cachedLocaleData.putIfAbsent(key, dateTimePatterns);
		}
		formatData = DateFormatSymbols.getInstanceRef(loc);
		if ((timeStyle >= 0) && (dateStyle >= 0)) {
			Object[] dateTimeArgs = { dateTimePatterns[timeStyle], dateTimePatterns[dateStyle + 4] };
			pattern = MessageFormat.format(dateTimePatterns[8], dateTimeArgs);
		} else if (timeStyle >= 0) {
			pattern = dateTimePatterns[timeStyle];
		} else if (dateStyle >= 0) {
			pattern = dateTimePatterns[dateStyle + 4];
		} else {
			throw new IllegalArgumentException("No date or time style specified");
		}

		initialize(loc);
	}

	/* Initialize compiledPattern and numberFormat fields */
	private void initialize(Locale loc) {
		// Verify and compile the given pattern.
		compiledPattern = compile(pattern);

		/* try the cache first */
		numberFormat = cachedNumberFormatData.get(loc);
		if (numberFormat == null) { /* cache miss */
			numberFormat = NumberFormat.getIntegerInstance(loc);
			numberFormat.setGroupingUsed(false);

			/* update cache */
			cachedNumberFormatData.putIfAbsent(loc, numberFormat);
		}
		numberFormat = (NumberFormat) numberFormat.clone();

		initializeDefaultCentury();
	}

	private void initializeCalendar(Locale loc) {
		if (calendar == null) {
			assert loc != null;
			// The format object must be constructed using the symbols for this zone.
			// However, the calendar should use the current default TimeZone.
			// If this is not contained in the locale zone strings, then the zone
			// will be formatted using generic GMT+/-H:MM nomenclature.
			calendar = Calendar.getInstance(TimeZone.getDefault(), loc);
		}
	}

	private String getKey() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCalendarName()).append('.');
		sb.append(locale.getLanguage()).append('_').append(locale.getCountry()).append('_').append(locale.getVariant());
		return sb.toString();
	}

	private char[] compile(String pattern) {
		int length = pattern.length();
		boolean inQuote = false;
		StringBuilder compiledPattern = new StringBuilder(length * 2);
		StringBuilder tmpBuffer = null;
		int count = 0;
		int lastTag = -1;

		for (int i = 0; i < length; i++) {
			char c = pattern.charAt(i);

			if (c == '\'') {
				// '' is treated as a single quote regardless of being
				// in a quoted section.
				if ((i + 1) < length) {
					c = pattern.charAt(i + 1);
					if (c == '\'') {
						i++;
						if (count != 0) {
							encode(lastTag, count, compiledPattern);
							lastTag = -1;
							count = 0;
						}
						if (inQuote) {
							tmpBuffer.append(c);
						} else {
							compiledPattern.append((char) (TAG_QUOTE_ASCII_CHAR << 8 | c));
						}
						continue;
					}
				}
				if (!inQuote) {
					if (count != 0) {
						encode(lastTag, count, compiledPattern);
						lastTag = -1;
						count = 0;
					}
					if (tmpBuffer == null) {
						tmpBuffer = new StringBuilder(length);
					} else {
						tmpBuffer.setLength(0);
					}
					inQuote = true;
				} else {
					int len = tmpBuffer.length();
					if (len == 1) {
						char ch = tmpBuffer.charAt(0);
						if (ch < 128) {
							compiledPattern.append((char) (TAG_QUOTE_ASCII_CHAR << 8 | ch));
						} else {
							compiledPattern.append((char) (TAG_QUOTE_CHARS << 8 | 1));
							compiledPattern.append(ch);
						}
					} else {
						encode(TAG_QUOTE_CHARS, len, compiledPattern);
						compiledPattern.append(tmpBuffer);
					}
					inQuote = false;
				}
				continue;
			}
			if (inQuote) {
				tmpBuffer.append(c);
				continue;
			}
			if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
				if (count != 0) {
					encode(lastTag, count, compiledPattern);
					lastTag = -1;
					count = 0;
				}
				if (c < 128) {
					// In most cases, c would be a delimiter, such as ':'.
					compiledPattern.append((char) (TAG_QUOTE_ASCII_CHAR << 8 | c));
				} else {
					// Take any contiguous non-ASCII alphabet characters and
					// put them in a single TAG_QUOTE_CHARS.
					int j;
					for (j = i + 1; j < length; j++) {
						char d = pattern.charAt(j);
						if (d == '\'' || (d >= 'a' && d <= 'z' || d >= 'A' && d <= 'Z')) {
							break;
						}
					}
					compiledPattern.append((char) (TAG_QUOTE_CHARS << 8 | (j - i)));
					for (; i < j; i++) {
						compiledPattern.append(pattern.charAt(i));
					}
					i--;
				}
				continue;
			}

			int tag;
			if ((tag = DateFormatSymbols.patternChars.indexOf(c)) == -1) {
				throw new IllegalArgumentException("Illegal pattern character " + "'" + c + "'");
			}
			if (lastTag == -1 || lastTag == tag) {
				lastTag = tag;
				count++;
				continue;
			}
			encode(lastTag, count, compiledPattern);
			lastTag = tag;
			count = 1;
		}

		if (inQuote) {
			throw new IllegalArgumentException("Unterminated quote");
		}

		if (count != 0) {
			encode(lastTag, count, compiledPattern);
		}

		// Copy the compiled pattern to a char array
		int len = compiledPattern.length();
		char[] r = new char[len];
		compiledPattern.getChars(0, len, r, 0);
		return r;
	}

	/**
	 * Encodes the given tag and length and puts encoded char(s) into buffer.
	 */
	private static final void encode(int tag, int length, StringBuilder buffer) {
		if (length < 255) {
			buffer.append((char) (tag << 8 | length));
		} else {
			buffer.append((char) ((tag << 8) | 0xff));
			buffer.append((char) (length >>> 16));
			buffer.append((char) (length & 0xffff));
		}
	}

	/*
	 * Initialize the fields we use to disambiguate ambiguous years. Separate so we can call it from readObject().
	 */
	private void initializeDefaultCentury() {
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.YEAR, -80);
		parseAmbiguousDatesAsAfter(calendar.getTime());
	}

	/*
	 * Define one-century window into which to disambiguate dates using two-digit years.
	 */
	private void parseAmbiguousDatesAsAfter(Date startDate) {
		defaultCenturyStart = startDate;
		calendar.setTime(startDate);
		defaultCenturyStartYear = calendar.get(Calendar.YEAR);
	}

	/**
	 * Sets the 100-year period 2-digit years will be interpreted as being in to begin on the date the user specifies.
	 * 
	 * @param startDate
	 *            During parsing, two digit years will be placed in the range <code>startDate</code> to
	 *            <code>startDate + 100 years</code>.
	 * @see #get2DigitYearStart
	 * @since 1.2
	 */
	public void set2DigitYearStart(Date startDate) {
		parseAmbiguousDatesAsAfter(new Date(startDate.getTime()));
	}

	public Date get2DigitYearStart() {
		return (Date) defaultCenturyStart.clone();
	}

	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
		pos.setBeginIndex(0);
		pos.setEndIndex(0);
		return format(date, toAppendTo, new FieldPositionDelegate(pos));
	}

	private StringBuffer format(Date date, StringBuffer toAppendTo, FieldDelegate delegate) {
		// Convert input date to time field list
		calendar.setTime(date);

		boolean useDateFormatSymbols = useDateFormatSymbols();

		for (int i = 0; i < compiledPattern.length;) {
			int tag = compiledPattern[i] >>> 8;
			int count = compiledPattern[i++] & 0xff;
			if (count == 255) {
				count = compiledPattern[i++] << 16;
				count |= compiledPattern[i++];
			}

			switch (tag) {
			case TAG_QUOTE_ASCII_CHAR:
				toAppendTo.append((char) count);
				break;

			case TAG_QUOTE_CHARS:
				toAppendTo.append(compiledPattern, i, count);
				i += count;
				break;

			default:
				subFormat(tag, count, delegate, toAppendTo, useDateFormatSymbols);
				break;
			}
		}
		return toAppendTo;
	}

	private static final int[] PATTERN_INDEX_TO_CALENDAR_FIELD = { Calendar.ERA, Calendar.YEAR, Calendar.MONTH,
			Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
			Calendar.MILLISECOND, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_YEAR, Calendar.DAY_OF_WEEK_IN_MONTH,
			Calendar.WEEK_OF_YEAR, Calendar.WEEK_OF_MONTH, Calendar.AM_PM, Calendar.HOUR, Calendar.HOUR,
			Calendar.ZONE_OFFSET, Calendar.ZONE_OFFSET };

	private static final int[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD = { DateFormat.ERA_FIELD, DateFormat.YEAR_FIELD,
			DateFormat.MONTH_FIELD, DateFormat.DATE_FIELD, DateFormat.HOUR_OF_DAY1_FIELD,
			DateFormat.HOUR_OF_DAY0_FIELD, DateFormat.MINUTE_FIELD, DateFormat.SECOND_FIELD,
			DateFormat.MILLISECOND_FIELD, DateFormat.DAY_OF_WEEK_FIELD, DateFormat.DAY_OF_YEAR_FIELD,
			DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD, DateFormat.WEEK_OF_YEAR_FIELD, DateFormat.WEEK_OF_MONTH_FIELD,
			DateFormat.AM_PM_FIELD, DateFormat.HOUR1_FIELD, DateFormat.HOUR0_FIELD, DateFormat.TIMEZONE_FIELD,
			DateFormat.TIMEZONE_FIELD, };

	private static final Field[] PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID = { Field.ERA, Field.YEAR, Field.MONTH,
			Field.DAY_OF_MONTH, Field.HOUR_OF_DAY1, Field.HOUR_OF_DAY0, Field.MINUTE, Field.SECOND, Field.MILLISECOND,
			Field.DAY_OF_WEEK, Field.DAY_OF_YEAR, Field.DAY_OF_WEEK_IN_MONTH, Field.WEEK_OF_YEAR, Field.WEEK_OF_MONTH,
			Field.AM_PM, Field.HOUR1, Field.HOUR0, Field.TIME_ZONE, Field.TIME_ZONE, };

	private void subFormat(int patternCharIndex, int count, FieldDelegate delegate, StringBuffer buffer,
			boolean useDateFormatSymbols) {
		int maxIntCount = Integer.MAX_VALUE;
		String current = null;
		int beginOffset = buffer.length();

		int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];
		int value = calendar.get(field);
		int style = (count >= 4) ? Calendar.LONG : Calendar.SHORT;
		if (!useDateFormatSymbols) {
			current = calendar.getDisplayName(field, style, locale);
		}

		// Note: zeroPaddingNumber() assumes that maxDigits is either
		// 2 or maxIntCount. If we make any changes to this,
		// zeroPaddingNumber() must be fixed.

		switch (patternCharIndex) {
		case 0: // 'G' - ERA
			if (useDateFormatSymbols) {
				String[] eras = formatData.getEras();
				if (value < eras.length)
					current = eras[value];
			}
			if (current == null)
				current = "";
			break;

		case 1: // 'y' - YEAR
			if (calendar instanceof GregorianCalendar) {
				if (count >= 4)
					zeroPaddingNumber(value, count, maxIntCount, buffer);
				else
					// count < 4
					zeroPaddingNumber(value, 2, 2, buffer); // clip 1996 to 96
			} else {
				if (current == null) {
					zeroPaddingNumber(value, style == Calendar.LONG ? 1 : count, maxIntCount, buffer);
				}
			}
			break;

		case 2: // 'M' - MONTH
			if (useDateFormatSymbols) {
				String[] months;
				if (count >= 4) {
					months = formatData.getMonths();
					current = months[value];
				} else if (count == 3) {
					months = formatData.getShortMonths();
					current = months[value];
				}
			} else {
				if (count < 3) {
					current = null;
				}
			}
			if (current == null) {
				zeroPaddingNumber(value + 1, count, maxIntCount, buffer);
			}
			break;

		case 4: // 'k' - HOUR_OF_DAY: 1-based. eg, 23:59 + 1 hour =>> 24:59
			if (current == null) {
				if (value == 0)
					zeroPaddingNumber(calendar.getMaximum(Calendar.HOUR_OF_DAY) + 1, count, maxIntCount, buffer);
				else
					zeroPaddingNumber(value, count, maxIntCount, buffer);
			}
			break;

		case 9: // 'E' - DAY_OF_WEEK
			if (useDateFormatSymbols) {
				String[] weekdays;
				if (count >= 4) {
					weekdays = formatData.getWeekdays();
					current = weekdays[value];
				} else { // count < 4, use abbreviated form if exists
					weekdays = formatData.getShortWeekdays();
					current = weekdays[value];
				}
			}
			break;

		case 14: // 'a' - AM_PM
			if (useDateFormatSymbols) {
				String[] ampm = formatData.getAmPmStrings();
				current = ampm[value];
			}
			break;

		case 15: // 'h' - HOUR:1-based. eg, 11PM + 1 hour =>> 12 AM
			if (current == null) {
				if (value == 0)
					zeroPaddingNumber(calendar.getLeastMaximum(Calendar.HOUR) + 1, count, maxIntCount, buffer);
				else
					zeroPaddingNumber(value, count, maxIntCount, buffer);
			}
			break;

		case 17: // 'z' - ZONE_OFFSET
			if (current == null) {
				if (formatData.locale == null || formatData.isZoneStringsSet) {
					int zoneIndex = formatData.getZoneIndex(calendar.getTimeZone().getID());
					if (zoneIndex == -1) {
						value = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);
						buffer.append(ZoneInfoFile.toCustomID(value));
					} else {
						int index = (calendar.get(Calendar.DST_OFFSET) == 0) ? 1 : 3;
						if (count < 4) {
							// Use the short name
							index++;
						}
						String[][] zoneStrings = formatData.getZoneStringsWrapper();
						buffer.append(zoneStrings[zoneIndex][index]);
					}
				} else {
					TimeZone tz = calendar.getTimeZone();
					boolean daylight = (calendar.get(Calendar.DST_OFFSET) != 0);
					int tzstyle = (count < 4 ? TimeZone.SHORT : TimeZone.LONG);
					buffer.append(tz.getDisplayName(daylight, tzstyle, formatData.locale));
				}
			}
			break;

		case 18: // 'Z' - ZONE_OFFSET ("-/+hhmm" form)
			value = (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / 60000;

			int width = 4;
			if (value >= 0) {
				buffer.append('+');
			} else {
				width++;
			}

			int num = (value / 60) * 100 + (value % 60);
			CalendarUtils.sprintf0d(buffer, num, width);
			break;

		default:
			// case 3: // 'd' - DATE
			// case 5: // 'H' - HOUR_OF_DAY:0-based. eg, 23:59 + 1 hour =>> 00:59
			// case 6: // 'm' - MINUTE
			// case 7: // 's' - SECOND
			// case 8: // 'S' - MILLISECOND
			// case 10: // 'D' - DAY_OF_YEAR
			// case 11: // 'F' - DAY_OF_WEEK_IN_MONTH
			// case 12: // 'w' - WEEK_OF_YEAR
			// case 13: // 'W' - WEEK_OF_MONTH
			// case 16: // 'K' - HOUR: 0-based. eg, 11PM + 1 hour =>> 0 AM
			if (current == null) {
				zeroPaddingNumber(value, count, maxIntCount, buffer);
			}
			break;
		} // switch (patternCharIndex)

		if (current != null) {
			buffer.append(current);
		}

		int fieldID = PATTERN_INDEX_TO_DATE_FORMAT_FIELD[patternCharIndex];
		Field f = PATTERN_INDEX_TO_DATE_FORMAT_FIELD_ID[patternCharIndex];

		delegate.formatted(fieldID, f, f, beginOffset, buffer.length(), buffer);
	}

	private final void zeroPaddingNumber(int value, int minDigits, int maxDigits, StringBuffer buffer) {
		// Optimization for 1, 2 and 4 digit numbers. This should
		// cover most cases of formatting date/time related items.
		// Note: This optimization code assumes that maxDigits is
		// either 2 or Integer.MAX_VALUE (maxIntCount in format()).
		try {
			if (zeroDigit == 0) {
				zeroDigit = ((DecimalFormat) numberFormat).getDecimalFormatSymbols().getZeroDigit();
			}
			if (value >= 0) {
				if (value < 100 && minDigits >= 1 && minDigits <= 2) {
					if (value < 10) {
						if (minDigits == 2) {
							buffer.append(zeroDigit);
						}
						buffer.append((char) (zeroDigit + value));
					} else {
						buffer.append((char) (zeroDigit + value / 10));
						buffer.append((char) (zeroDigit + value % 10));
					}
					return;
				} else if (value >= 1000 && value < 10000) {
					if (minDigits == 4) {
						buffer.append((char) (zeroDigit + value / 1000));
						value %= 1000;
						buffer.append((char) (zeroDigit + value / 100));
						value %= 100;
						buffer.append((char) (zeroDigit + value / 10));
						buffer.append((char) (zeroDigit + value % 10));
						return;
					}
					if (minDigits == 2 && maxDigits == 2) {
						zeroPaddingNumber(value % 100, 2, 2, buffer);
						return;
					}
				}
			}
		} catch (Exception e) {
		}

		numberFormat.setMinimumIntegerDigits(minDigits);
		numberFormat.setMaximumIntegerDigits(maxDigits);
		numberFormat.format((long) value, buffer, DontCareFieldPosition.INSTANCE);
	}

	public Date parse(String text, ParsePosition pos) {

		checkNegativeNumberExpression();

		int start = pos.getIndex();
		int oldStart = start;
		int textLength = text.length();

		calendar.clear(); // Clears all the time fields

		boolean[] ambiguousYear = { false };

		for (int i = 0; i < compiledPattern.length;) {
			int tag = compiledPattern[i] >>> 8;
			int count = compiledPattern[i++] & 0xff;
			if (count == 255) {
				count = compiledPattern[i++] << 16;
				count |= compiledPattern[i++];
			}

			switch (tag) {
			case TAG_QUOTE_ASCII_CHAR:
				if (start >= textLength || text.charAt(start) != (char) count) {
					pos.setIndex(oldStart);
					pos.setErrorIndex(start);
					return null;
				}
				start++;
				break;

			case TAG_QUOTE_CHARS:
				while (count-- > 0) {
					if (start >= textLength || text.charAt(start) != compiledPattern[i++]) {
						pos.setIndex(oldStart);
						pos.setErrorIndex(start);
						return null;
					}
					start++;
				}
				break;

			default:

				boolean obeyCount = false;

				boolean useFollowingMinusSignAsDelimiter = false;

				if (i < compiledPattern.length) {
					int nextTag = compiledPattern[i] >>> 8;
					if (!(nextTag == TAG_QUOTE_ASCII_CHAR || nextTag == TAG_QUOTE_CHARS)) {
						obeyCount = true;
					}

					if (hasFollowingMinusSign && (nextTag == TAG_QUOTE_ASCII_CHAR || nextTag == TAG_QUOTE_CHARS)) {
						int c;
						if (nextTag == TAG_QUOTE_ASCII_CHAR) {
							c = compiledPattern[i] & 0xff;
						} else {
							c = compiledPattern[i + 1];
						}

						if (c == minusSign) {
							useFollowingMinusSignAsDelimiter = true;
						}
					}
				}
				start = subParse(text, start, tag, count, obeyCount, ambiguousYear, pos,
						useFollowingMinusSignAsDelimiter);
				if (start < 0) {
					pos.setIndex(oldStart);
					return null;
				}
			}
			// end switch(tag)
		}

		pos.setIndex(start);

		Date parsedDate;
		try {
			if (ambiguousYear[0]) // If this is true then the two-digit year == the default start year
			{
				// We need a copy of the fields, and we need to avoid triggering a call to
				// complete(), which will recalculate the fields. Since we can't access
				// the fields[] array in Calendar, we clone the entire object. This will
				// stop working if Calendar.clone() is ever rewritten to call complete().
				Calendar savedCalendar = (Calendar) calendar.clone();
				parsedDate = calendar.getTime();
				if (parsedDate.before(defaultCenturyStart)) {
					// We can't use add here because that does a complete() first.
					savedCalendar.set(Calendar.YEAR, defaultCenturyStartYear + 100);
					parsedDate = savedCalendar.getTime();
				}
			} else
				parsedDate = calendar.getTime();
		}
		// An IllegalArgumentException will be thrown by Calendar.getTime()
		// if any fields are out of range, e.g., MONTH == 17.
		catch (IllegalArgumentException e) {
			pos.setErrorIndex(start);
			pos.setIndex(oldStart);
			return null;
		}

		return parsedDate;
	}

	private int matchString(String text, int start, int field, String[] data) {
		int i = 0;
		int count = data.length;

		if (field == Calendar.DAY_OF_WEEK)
			i = 1;
		int bestMatchLength = 0, bestMatch = -1;
		for (; i < count; ++i) {
			int length = data[i].length();
			if (length > bestMatchLength && text.regionMatches(true, start, data[i], 0, length)) {
				bestMatch = i;
				bestMatchLength = length;
			}
		}
		if (bestMatch >= 0) {
			calendar.set(field, bestMatch);
			return start + bestMatchLength;
		}
		return -start;
	}

	private int matchString(String text, int start, int field, Map<String, Integer> data) {
		if (data != null) {
			String bestMatch = null;

			for (String name : data.keySet()) {
				int length = name.length();
				if (bestMatch == null || length > bestMatch.length()) {
					if (text.regionMatches(true, start, name, 0, length)) {
						bestMatch = name;
					}
				}
			}

			if (bestMatch != null) {
				calendar.set(field, data.get(bestMatch));
				return start + bestMatch.length();
			}
		}
		return -start;
	}

	private int matchZoneString(String text, int start, String[] zoneNames) {
		for (int i = 1; i <= 4; ++i) {
			// Checking long and short zones [1 & 2],
			// and long and short daylight [3 & 4].
			String zoneName = zoneNames[i];
			if (text.regionMatches(true, start, zoneName, 0, zoneName.length())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * find time zone 'text' matched zoneStrings and set to internal calendar.
	 */
	private int subParseZoneString(String text, int start) {
		boolean useSameName = false; // true if standard and daylight time use the same abbreviation.
		TimeZone currentTimeZone = getTimeZone();

		int zoneIndex = formatData.getZoneIndex(currentTimeZone.getID());
		TimeZone tz = null;
		String[][] zoneStrings = formatData.getZoneStringsWrapper();
		String[] zoneNames = null;
		int nameIndex = 0;
		if (zoneIndex != -1) {
			zoneNames = zoneStrings[zoneIndex];
			if ((nameIndex = matchZoneString(text, start, zoneNames)) > 0) {
				if (nameIndex <= 2) {
					// Check if the standard name (abbr) and the daylight name are the same.
					useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
				}
				tz = TimeZone.getTimeZone(zoneNames[0]);
			}
		}
		if (tz == null) {
			zoneIndex = formatData.getZoneIndex(TimeZone.getDefault().getID());
			if (zoneIndex != -1) {
				zoneNames = zoneStrings[zoneIndex];
				if ((nameIndex = matchZoneString(text, start, zoneNames)) > 0) {
					if (nameIndex <= 2) {
						useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
					}
					tz = TimeZone.getTimeZone(zoneNames[0]);
				}
			}
		}
		if (tz == null) {
			int len = zoneStrings.length;
			for (int i = 0; i < len; i++) {
				zoneNames = zoneStrings[i];
				if ((nameIndex = matchZoneString(text, start, zoneNames)) > 0) {
					if (nameIndex <= 2) {
						useSameName = zoneNames[nameIndex].equalsIgnoreCase(zoneNames[nameIndex + 2]);
					}
					tz = TimeZone.getTimeZone(zoneNames[0]);
					break;
				}
			}
		}
		if (tz != null) { // Matched any ?
			if (!tz.equals(currentTimeZone)) {
				setTimeZone(tz);
			}
			int dstAmount = (nameIndex >= 3) ? tz.getDSTSavings() : 0;
			if (!(useSameName || (nameIndex >= 3 && dstAmount == 0))) {
				calendar.set(Calendar.DST_OFFSET, dstAmount);
			}
			return (start + zoneNames[nameIndex].length());
		}
		return 0;
	}

	private int incPos(ParsePosition pos) {
		int index = pos.getIndex();
		index++;
		pos.setIndex(index);
		return index;
	}

	private int decPos(ParsePosition pos) {
		int index = pos.getIndex();
		index--;
		pos.setIndex(index);
		return index;
	}

	private int subParse(String text, int start, int patternCharIndex, int count, boolean obeyCount,
			boolean[] ambiguousYear, ParsePosition origPos, boolean useFollowingMinusSignAsDelimiter) {
		Number number = null;
		int value = 0;
		ParsePosition pos = new ParsePosition(0);
		pos.setIndex(start);
		int field = PATTERN_INDEX_TO_CALENDAR_FIELD[patternCharIndex];

		// If there are any spaces here, skip over them. If we hit the end
		// of the string, then fail.
		for (;;) {
			if (pos.getIndex() >= text.length()) {
				origPos.setErrorIndex(start);
				return -1;
			}
			char c = text.charAt(pos.getIndex());
			if (c != ' ' && c != '\t')
				break;
			incPos(pos);
		}

		parsing: {

			if (patternCharIndex == 4 /* HOUR_OF_DAY1_FIELD */|| patternCharIndex == 15 /* HOUR1_FIELD */
					|| (patternCharIndex == 2 /* MONTH_FIELD */&& count <= 2) || patternCharIndex == 1) /* YEAR_FIELD */
			{
				if (obeyCount) {
					if ((start + count) > text.length()) {
						break parsing;
					}
					number = numberFormat.parse(text.substring(0, start + count), pos);
				} else {
					number = numberFormat.parse(text, pos);
				}
				if (number == null) {
					if (patternCharIndex != 1 || calendar instanceof GregorianCalendar) {
						break parsing;
					}
				} else {
					value = number.intValue();

					if (useFollowingMinusSignAsDelimiter
							&& (value < 0)
							&& (((pos.getIndex() < text.length()) && (text.charAt(pos.getIndex()) != minusSign)) || ((pos
									.getIndex() == text.length()) && (text.charAt(pos.getIndex() - 1) == minusSign)))) {
						value = -value;
						decPos(pos);
					}
				}
			}

			boolean useDateFormatSymbols = useDateFormatSymbols();

			int index;
			switch (patternCharIndex) {
			case 0: // 'G' - ERA
				if (useDateFormatSymbols) {
					if ((index = matchString(text, start, Calendar.ERA, formatData.getEras())) > 0) {
						return index;
					}
				} else {
					Map<String, Integer> map = calendar.getDisplayNames(field, Calendar.ALL_STYLES, locale);
					if ((index = matchString(text, start, field, map)) > 0) {
						return index;
					}
				}
				break parsing;

			case 1: // 'y' - YEAR
				if (!(calendar instanceof GregorianCalendar)) {
					// calendar might have text representations for year values,
					// such as "\u5143" in JapaneseImperialCalendar.
					int style = (count >= 4) ? Calendar.LONG : Calendar.SHORT;
					Map<String, Integer> map = calendar.getDisplayNames(field, style, locale);
					if (map != null) {
						if ((index = matchString(text, start, field, map)) > 0) {
							return index;
						}
					}
					calendar.set(field, value);
					return pos.getIndex();
				}

				if (count <= 2 && (pos.getIndex() - start) == 2 && Character.isDigit(text.charAt(start))
						&& Character.isDigit(text.charAt(start + 1))) {

					int ambiguousTwoDigitYear = defaultCenturyStartYear % 100;
					ambiguousYear[0] = value == ambiguousTwoDigitYear;
					value += (defaultCenturyStartYear / 100) * 100 + (value < ambiguousTwoDigitYear ? 100 : 0);
				}
				calendar.set(Calendar.YEAR, value);
				return pos.getIndex();

			case 2: // 'M' - MONTH
				if (count <= 2) // i.e., M or MM.
				{

					calendar.set(Calendar.MONTH, value - 1);
					return pos.getIndex();
				}

				if (useDateFormatSymbols) {
					// count >= 3 // i.e., MMM or MMMM
					// Want to be able to parse both short and long forms.
					// Try count == 4 first:
					int newStart = 0;
					if ((newStart = matchString(text, start, Calendar.MONTH, formatData.getMonths())) > 0) {
						return newStart;
					}
					// count == 4 failed, now try count == 3
					if ((index = matchString(text, start, Calendar.MONTH, formatData.getShortMonths())) > 0) {
						return index;
					}
				} else {
					Map<String, Integer> map = calendar.getDisplayNames(field, Calendar.ALL_STYLES, locale);
					if ((index = matchString(text, start, field, map)) > 0) {
						return index;
					}
				}
				break parsing;

			case 4: // 'k' - HOUR_OF_DAY: 1-based. eg, 23:59 + 1 hour =>> 24:59
				// [We computed 'value' above.]
				if (value == calendar.getMaximum(Calendar.HOUR_OF_DAY) + 1)
					value = 0;
				calendar.set(Calendar.HOUR_OF_DAY, value);
				return pos.getIndex();

			case 9: { // 'E' - DAY_OF_WEEK
				if (useDateFormatSymbols) {
					// Want to be able to parse both short and long forms.
					// Try count == 4 (DDDD) first:
					int newStart = 0;
					if ((newStart = matchString(text, start, Calendar.DAY_OF_WEEK, formatData.getWeekdays())) > 0) {
						return newStart;
					}
					// DDDD failed, now try DDD
					if ((index = matchString(text, start, Calendar.DAY_OF_WEEK, formatData.getShortWeekdays())) > 0) {
						return index;
					}
				} else {
					int[] styles = { Calendar.LONG, Calendar.SHORT };
					for (int style : styles) {
						Map<String, Integer> map = calendar.getDisplayNames(field, style, locale);
						if ((index = matchString(text, start, field, map)) > 0) {
							return index;
						}
					}
				}
			}
				break parsing;

			case 14: // 'a' - AM_PM
				if (useDateFormatSymbols) {
					if ((index = matchString(text, start, Calendar.AM_PM, formatData.getAmPmStrings())) > 0) {
						return index;
					}
				} else {
					Map<String, Integer> map = calendar.getDisplayNames(field, Calendar.ALL_STYLES, locale);
					if ((index = matchString(text, start, field, map)) > 0) {
						return index;
					}
				}
				break parsing;

			case 15: // 'h' - HOUR:1-based. eg, 11PM + 1 hour =>> 12 AM
				// [We computed 'value' above.]
				if (value == calendar.getLeastMaximum(Calendar.HOUR) + 1)
					value = 0;
				calendar.set(Calendar.HOUR, value);
				return pos.getIndex();

			case 17: // 'z' - ZONE_OFFSET
			case 18: // 'Z' - ZONE_OFFSET
				// First try to parse generic forms such as GMT-07:00. Do this first
				// in case localized TimeZoneNames contains the string "GMT"
				// for a zone; in that case, we don't want to match the first three
				// characters of GMT+/-hh:mm etc.
			{
				int sign = 0;
				int offset;

				// For time zones that have no known names, look for strings
				// of the form:
				// GMT[+-]hours:minutes or
				// GMT.
				if ((text.length() - start) >= GMT.length() && text.regionMatches(true, start, GMT, 0, GMT.length())) {
					int num;
					calendar.set(Calendar.DST_OFFSET, 0);
					pos.setIndex(start + GMT.length());

					try { // try-catch for "GMT" only time zone string
						char c = text.charAt(pos.getIndex());
						if (c == '+') {
							sign = 1;
						} else if (c == '-') {
							sign = -1;
						}
					} catch (StringIndexOutOfBoundsException e) {
					}

					if (sign == 0) { /* "GMT" without offset */
						calendar.set(Calendar.ZONE_OFFSET, 0);
						return pos.getIndex();
					}

					// Look for hours.
					try {
						char c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						num = c - '0';

						if (text.charAt(incPos(pos)) != ':') {
							c = text.charAt(pos.getIndex());
							if (c < '0' || c > '9') { /* must be from '0' to '9'. */
								break parsing;
							}
							num *= 10;
							num += c - '0';
							incPos(pos);
						}
						if (num > 23) {
							decPos(pos);
							break parsing;
						}
						if (text.charAt(pos.getIndex()) != ':') {
							break parsing;
						}

						// Look for minutes.
						offset = num * 60;
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						num = c - '0';
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						num *= 10;
						num += c - '0';

						if (num > 59) {
							break parsing;
						}
					} catch (StringIndexOutOfBoundsException e) {
						break parsing;
					}
					offset += num;
					// Fall through for final processing below of 'offset' and 'sign'.
				} else {
					// If the first character is a sign, look for numeric timezones of
					// the form [+-]hhmm as specified by RFC 822. Otherwise, check
					// for named time zones by looking through the locale data from
					// the TimeZoneNames strings.
					try {
						char c = text.charAt(pos.getIndex());
						if (c == '+') {
							sign = 1;
						} else if (c == '-') {
							sign = -1;
						} else {
							// Try parsing the text as a time zone name (abbr).
							int i = subParseZoneString(text, pos.getIndex());
							if (i != 0) {
								return i;
							}
							break parsing;
						}

						// Parse the text as an RFC 822 time zone string. This code is
						// actually a little more permissive than RFC 822. It will
						// try to do its best with numbers that aren't strictly 4
						// digits long.

						// Look for hh.
						int hours = 0;
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						hours = c - '0';
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						hours *= 10;
						hours += c - '0';

						if (hours > 23) {
							break parsing;
						}

						// Look for mm.
						int minutes = 0;
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						minutes = c - '0';
						c = text.charAt(incPos(pos));
						if (c < '0' || c > '9') { /* must be from '0' to '9'. */
							break parsing;
						}
						minutes *= 10;
						minutes += c - '0';

						if (minutes > 59) {
							break parsing;
						}

						offset = hours * 60 + minutes;
					} catch (StringIndexOutOfBoundsException e) {
						break parsing;
					}
				}

				// Do the final processing for both of the above cases. We only
				// arrive here if the form GMT+/-... or an RFC 822 form was seen.
				if (sign != 0) {
					offset *= MILLIS_PER_MINUTE * sign;
					calendar.set(Calendar.ZONE_OFFSET, offset);
					calendar.set(Calendar.DST_OFFSET, 0);
					return incPos(pos);
				}
			}
				break parsing;

			default:
				// case 3: // 'd' - DATE
				// case 5: // 'H' - HOUR_OF_DAY:0-based. eg, 23:59 + 1 hour =>> 00:59
				// case 6: // 'm' - MINUTE
				// case 7: // 's' - SECOND
				// case 8: // 'S' - MILLISECOND
				// case 10: // 'D' - DAY_OF_YEAR
				// case 11: // 'F' - DAY_OF_WEEK_IN_MONTH
				// case 12: // 'w' - WEEK_OF_YEAR
				// case 13: // 'W' - WEEK_OF_MONTH
				// case 16: // 'K' - HOUR: 0-based. eg, 11PM + 1 hour =>> 0 AM

				// Handle "generic" fields
				if (obeyCount) {
					if ((start + count) > text.length()) {
						break parsing;
					}
					number = numberFormat.parse(text.substring(0, start + count), pos);
				} else {
					number = numberFormat.parse(text, pos);
				}
				if (number != null) {

					value = number.intValue();

					if (useFollowingMinusSignAsDelimiter
							&& (value < 0)
							&& (((pos.getIndex() < text.length()) && (text.charAt(pos.getIndex()) != minusSign)) || ((pos
									.getIndex() == text.length()) && (text.charAt(pos.getIndex() - 1) == minusSign)))) {

						value = -value;
						decPos(pos);
					}

					calendar.set(field, value);
					return pos.getIndex();
				}
				break parsing;
			}
		}

		// Parsing failed.
		origPos.setErrorIndex(pos.getIndex());
		return -1;
	}

	private final String getCalendarName() {
		return calendar.getClass().getName();
	}

	private boolean useDateFormatSymbols() {
		if (useDateFormatSymbols) {
			return true;
		}
		return isGregorianCalendar() || locale == null;
	}

	private boolean isGregorianCalendar() {
		return "java.util.GregorianCalendar".equals(getCalendarName());
	}

	private String translatePattern(String pattern, String from, String to) {
		StringBuilder result = new StringBuilder();
		boolean inQuote = false;
		for (int i = 0; i < pattern.length(); ++i) {
			char c = pattern.charAt(i);
			if (inQuote) {
				if (c == '\'')
					inQuote = false;
			} else {
				if (c == '\'')
					inQuote = true;
				else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					int ci = from.indexOf(c);
					if (ci == -1)
						throw new IllegalArgumentException("Illegal pattern " + " character '" + c + "'");
					c = to.charAt(ci);
				}
			}
			result.append(c);
		}
		if (inQuote)
			throw new IllegalArgumentException("Unfinished quote in pattern");
		return result.toString();
	}

	public String toPattern() {
		return pattern;
	}

	public String toLocalizedPattern() {
		return translatePattern(pattern, DateFormatSymbols.patternChars, formatData.getLocalPatternChars());
	}

	public void applyPattern(String pattern) {
		compiledPattern = compile(pattern);
		this.pattern = pattern;
	}

	public void applyLocalizedPattern(String pattern) {
		String p = translatePattern(pattern, formatData.getLocalPatternChars(), DateFormatSymbols.patternChars);
		compiledPattern = compile(p);
		this.pattern = p;
	}

	public DateFormatSymbols getDateFormatSymbols() {
		return (DateFormatSymbols) formatData.clone();
	}

	public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
		this.formatData = (DateFormatSymbols) newFormatSymbols.clone();
		useDateFormatSymbols = true;
	}

	public Object clone() {
		SQLDateFormat other = (SQLDateFormat) super.clone();
		other.formatData = (DateFormatSymbols) formatData.clone();
		return other;
	}

	public int hashCode() {
		return pattern.hashCode();
	}

	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		SQLDateFormat that = (SQLDateFormat) obj;
		return (pattern.equals(that.pattern) && formatData.equals(that.formatData));
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();

		try {
			compiledPattern = compile(pattern);
		} catch (Exception e) {
			throw new InvalidObjectException("invalid pattern");
		}

		if (serialVersionOnStream < 1) {
			initializeDefaultCentury();
		} else {
			parseAmbiguousDatesAsAfter(defaultCenturyStart);
		}
		serialVersionOnStream = currentSerialVersion;

		TimeZone tz = getTimeZone();
		if (tz instanceof SimpleTimeZone) {
			String id = tz.getID();
			TimeZone zi = TimeZone.getTimeZone(id);
			if (zi != null && zi.hasSameRules(tz) && zi.getID().equals(id)) {
				setTimeZone(zi);
			}
		}
	}

	private void checkNegativeNumberExpression() {
		if ((numberFormat instanceof DecimalFormat) && !numberFormat.equals(originalNumberFormat)) {
			String numberPattern = ((DecimalFormat) numberFormat).toPattern();
			if (!numberPattern.equals(originalNumberPattern)) {
				hasFollowingMinusSign = false;
				int separatorIndex = numberPattern.indexOf(';');
				if (separatorIndex > -1) {
					int minusIndex = numberPattern.indexOf('-', separatorIndex);
					if ((minusIndex > numberPattern.lastIndexOf('0')) && (minusIndex > numberPattern.lastIndexOf('#'))) {
						hasFollowingMinusSign = true;
						minusSign = ((DecimalFormat) numberFormat).getDecimalFormatSymbols().getMinusSign();
					}
				}
				originalNumberPattern = numberPattern;
			}
			originalNumberFormat = numberFormat;
		}
	}

}
