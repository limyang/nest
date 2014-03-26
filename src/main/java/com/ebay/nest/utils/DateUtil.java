package com.ebay.nest.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil extends org.apache.commons.lang.time.DateUtils {

	public static final String DATE_FORMAT1 = "\\d{4}\\-\\d{2}\\-\\d{2}";

	public static final String DATE_FORMAT2 = "\\d{4}\\/\\d{2}\\/\\d{2}";

	// public static final String TIMESTAMP_FORMAT1 =
	// "\\d{4}\\-\\d{2}\\-\\d{2} \\d{2}:\\d{2}:\\d{2}";
	// public static final String TIME_FORMAT = "\\d{2}:\\d{2}:\\d{2}";

	// Get Current timestamp value with format 'yyyy-MM-dd HH:mm:ss'
	public static String currentTimeStamp() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTimeStamp0 = df.format(new Date());
		return currentTimeStamp0;
	}

	// Get Current date with format 'yyyy-MM-dd'
	public static String currentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = df.format(new Date());
		return currentDate;
	}

	public static Date parseDate(String date) {
		try {
			date = date.substring(0, 10);
			if (date.matches(DATE_FORMAT1)) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.parse(date);
			}
			if (date.matches(DATE_FORMAT2)) {
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				return sdf.parse(date);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}

	public static Timestamp parserTimestamp(String timestamp) {
		return Timestamp.valueOf(timestamp);
	}

	public static Time parseTime(String time) {
		return Time.valueOf(time);
	}

	public static Date timestamp2date(Timestamp ts) {
		int year = ts.getYear();
		int month = ts.getMonth();
		int day = ts.getDate();
		return new Date(year, month, day);
	}

	public static Time timestamp2time(Timestamp ts) {
		int hour = ts.getHours();
		int min = ts.getMinutes();
		int sec = ts.getSeconds();
		return new Time(hour, min, sec);
	}

	public static String dateString(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String timestampString(Date ts) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(ts);
	}

	public static String timeString(Date tm) {
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(tm);
	}
}
