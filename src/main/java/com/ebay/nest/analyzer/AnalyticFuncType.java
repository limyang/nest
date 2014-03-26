package com.ebay.nest.analyzer;

public enum AnalyticFuncType {
	AVG("AVG"),
	CORR("CORP"),
	COUNT("COUNT"),
	COVAR_POP("COVAR_POP"),
	COVAR_SAMP("COVAR_SAMP"),
	MAX("MAX"),
	MIN("MIN"),
	REGR_AVGX("REGR_AVGX"),
	REGR_COUNT("REGR_COUNT"),
	REGR_INTERCEPT("REGR_INTERCEPT"),
	REGR_R2("REGR_R2"),
	REGR_SLOPE("REGR_SLOPE"),
	REGR_SXX("REGR_SXX"),
	REGR_SXY("REGR_SXY"),
	REGR_SYY("REGR_SYY"),
	STDDEV_POP("STDDEV_POP"),
	STDDEV_SAMP("STDDEV_SAMP"),
	SUM("SUM"),
	VAR_POP("VAR_POP"),
	VAR_SAMP("VAR_SAMP"),
	
	//
	CSUM("CSUM"),
	MAVG("MAVG"),
	MDIFF("MDIFF"),
	MLINREG("MLINREG"),
	MSUM("MSUM"),
	PERCENT_RANK("MSUM"),
	QUANTILE("QUANTILE"),
	RANK("RANK"),
	ROW_NUMBER("ROW_NUMBER");
	
	private String name;

	AnalyticFuncType(String s) {
		name = s;

	}

	public String toString() {
		return name;
	}

	public static  AnalyticFuncType getType(String s) {

		for (AnalyticFuncType value : AnalyticFuncType.values()) {
			String name = value.toString();
			if (name.equalsIgnoreCase(s)) {
				return value;
			}
		}

		return null;
	}

}
