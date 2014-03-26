package com.ebay.nest._remove.ddi;

import java.io.File;

public class Uow {
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;

	public Uow(String value) {
		year = value.substring(0, 4);
		month = value.substring(4, 6);
		day = value.substring(6, 8);
		hour = value.substring(8, 10);
		minute = value.substring(10, 12);
		second = value.substring(12, 14);
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getHour() {
		return hour;
	}

	public String getMinute() {
		return minute;
	}

	public String getSecond() {
		return second;
	}

	public String getPathUtilDay() {
		return year + File.separator + month + File.separator + day;
	}

	public String getPathUtilDay00() {
		return year + File.separator + month + File.separator + day
				+ File.separator + "00";
	}

}