package com.ebay.nest.metrics;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PerfLogger {
	public static final String ScriptFunction = "script_function";

	protected static final ThreadLocal<PerfLogger> perfLogger = new ThreadLocal<PerfLogger>();

	protected final Map<String, Long> startTimes = new HashMap<String, Long>();
	protected final Map<String, Long> endTimes = new HashMap<String, Long>();

	static final private Log LOG = LogFactory.getLog(PerfLogger.class.getName());

	public PerfLogger() {
	}

	public static PerfLogger getPerfLogger() {
		return getPerfLogger(false);
	}

	public static PerfLogger getPerfLogger(boolean resetPerfLogger) {

		if (perfLogger.get() == null || resetPerfLogger) {
			perfLogger.set(new PerfLogger());
		}
		return perfLogger.get();

	}

	public void PerfLogBegin(String callerName, String method) {
		long startTime = System.currentTimeMillis();
		LOG.info("<PERFLOG method=" + method + " from=" + callerName + ">");
		startTimes.put(method, new Long(startTime));
	}

	public long PerfLogEnd(String callerName, String method) {
		Long startTime = startTimes.get(method);
		long endTime = System.currentTimeMillis();
		long duration = -1;

		endTimes.put(method, new Long(endTime));

		StringBuilder sb = new StringBuilder("</PERFLOG method=").append(method);
		if (startTime != null) {
			sb.append(" start=").append(startTime);
		}
		sb.append(" end=").append(endTime);
		if (startTime != null) {
			duration = endTime - startTime.longValue();
			sb.append(" duration=").append(duration);
		}
		sb.append(" from=").append(callerName).append(">");
		LOG.info(sb);

		return duration;
	}

	public Long getStartTime(String method) {
		return startTimes.get(method);
	}

	public Long getEndTime(String method) {
		return endTimes.get(method);
	}
}
