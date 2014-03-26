package com.ebay.nest.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebay.nest.NestConf;
import com.ebay.nest.NestConf.ConfVars;

public class VariableSubstitution {

	private static String prefix = "set: ";
	public static final String ENV_PREFIX = "env:";
	public static final String SYSTEM_PREFIX = "system:";
	public static final String HIVECONF_PREFIX = "nestconf:";
	public static final String HIVEVAR_PREFIX = "nestvar:";
	public static final String SET_COLUMN_NAME = "set";

	private static final Log l4j = LogFactory.getLog(VariableSubstitution.class);
	protected static Pattern varPat = Pattern.compile("\\$\\{[^\\}\\$\u0020]+\\}");

	private String getSubstitute(NestConf conf, String var) {
		String val = null;
		try {
			if (var.startsWith(SYSTEM_PREFIX)) {
				val = System.getProperty(var.substring(SYSTEM_PREFIX.length()));
			}
		} catch (SecurityException se) {
			l4j.warn("Unexpected SecurityException in Configuration", se);
		}
		if (val == null) {
			if (var.startsWith(ENV_PREFIX)) {
				val = System.getenv(var.substring(ENV_PREFIX.length()));
			}
		}
		if (val == null) {
			if (var.startsWith(HIVECONF_PREFIX)) {
				val = conf.get(var.substring(HIVECONF_PREFIX.length()));
			}
		}
		return val;
	}

	public String substitute(NestConf conf, String expr) {

		if (conf.getBoolVar(ConfVars.HIVEVARIABLESUBSTITUTE)) {
			l4j.debug("Substitution is on: " + expr);
		} else {
			return expr;
		}
		if (expr == null) {
			return null;
		}
		Matcher match = varPat.matcher("");
		String eval = expr;
		for (int s = 0; s < conf.getIntVar(ConfVars.HIVEVARIABLESUBSTITUTEDEPTH); s++) {
			match.reset(eval);
			if (!match.find()) {
				return eval;
			}
			String var = match.group();
			var = var.substring(2, var.length() - 1);
			String val = getSubstitute(conf, var);

			if (val == null) {
				l4j.debug("Interpolation result: " + eval);
				return eval;
			}
			eval = eval.substring(0, match.start()) + val + eval.substring(match.end());
		}
		throw new IllegalStateException("Variable substitution depth too large: "
				+ conf.getIntVar(ConfVars.HIVEVARIABLESUBSTITUTEDEPTH) + " " + expr);
	}
}
