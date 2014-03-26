package com.ebay.nest.metrics;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MetricsTimer {
	static final private Log LOG = LogFactory.getLog(MetricsTimer.class.getName());

	private MetricsTimer() {
		// block
	}

	public static class MetricsScope {

		final String name;
		final String numCounter;
		final String timeCounter;
		final String avgTimeCounter;

		private boolean isOpen = false;
		private Long startTime = null;

		private MetricsScope(String name) {
			this.name = name;
			this.numCounter = name + ".n";
			this.timeCounter = name + ".t";
			this.avgTimeCounter = name + ".avg_t";
		}

		public Long getNumCounter() {
			return (Long) MetricsTimer.get(numCounter);
		}

		public Long getTimeCounter() {
			return (Long) MetricsTimer.get(timeCounter);
		}

		public Double getAvgTimeCounter() {
			return (Double) MetricsTimer.get(avgTimeCounter);
		}

		public void open() {
			if (!isOpen) {
				isOpen = true;
				//LOG.info("<PERFLOG Scope=" + name + " >");
			}

		}

		public void begin() {
			if (isOpen) {
				startTime = System.currentTimeMillis();
			}
		}

		public void end() {
			if (isOpen) {
				Long endTime = System.currentTimeMillis();
				synchronized (metrics) {
					Long num = MetricsTimer.incrementCounter(numCounter);
					Long time = MetricsTimer.incrementCounter(timeCounter, endTime - startTime);
					if (num != null && time != null) {
						MetricsTimer.set(avgTimeCounter, Double.valueOf(time.doubleValue() / num.doubleValue()));
					}
				}
			}

		}

		public void close() {
			if (isOpen) {
				StringBuilder sb = new StringBuilder("</PERFLOG Scope=").append(name);
				if (startTime != null) {
					sb.append(" start_time=").append(startTime);
				}
				sb.append(" duration=").append(getTimeCounter());
				sb.append(" counter=").append(getNumCounter());
				sb.append(" average_time=").append(getAvgTimeCounter());
				sb.append(">");

				LOG.info(sb);

			}
			isOpen = false;
		}

		public void reopen() throws IOException {
			if (isOpen) {
				close();
			}
			open();
		}

	}

	private static final MetricsMBean metrics = new MetricsMBeanImpl();

	private static final ObjectName oname;
	static {
		try {
			oname = new ObjectName("com.ebay.nest.metrics:type=MetricsMBean");
		} catch (MalformedObjectNameException mone) {
			throw new RuntimeException(mone);
		}
	}

	private static final ThreadLocal<HashMap<String, MetricsScope>> threadLocalScopes = new ThreadLocal<HashMap<String, MetricsScope>>() {
		protected HashMap<String, MetricsScope> initialValue() {
			return new HashMap<String, MetricsScope>();
		}
	};

	private static boolean initialized = false;

	public static void init() throws Exception {
		synchronized (metrics) {
			if (!initialized) {
				MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
				mbs.registerMBean(metrics, oname);
				initialized = true;
			}
		}
	}

	public static Long incrementCounter(String name) {
		if (!initialized) {
			return null;
		}
		return incrementCounter(name, Long.valueOf(1));
	}

	public static Long incrementCounter(String name, long increment) {
		if (!initialized) {
			return null;
		}
		Long value;
		synchronized (metrics) {
			if (!metrics.hasKey(name)) {
				value = Long.valueOf(increment);
				set(name, value);
			} else {
				value = ((Long) get(name)) + increment;
				set(name, value);
			}
		}
		return value;
	}

	public static void set(String name, Object value) {
		if (!initialized) {
			return;
		}
		metrics.put(name, value);
	}

	public static Object get(String name) {
		if (!initialized) {
			return null;
		}
		return metrics.get(name);
	}

	public static MetricsScope startScope(String name) {
		if (!initialized) {
			return null;
		}
		if (threadLocalScopes.get().containsKey(name)) {
			threadLocalScopes.get().get(name).open();
		} else {
			threadLocalScopes.get().put(name, new MetricsScope(name));
		}
		return threadLocalScopes.get().get(name);
	}

	public static MetricsScope getScope(String name) {
		if (!initialized) {
			return null;
		}
		if (threadLocalScopes.get().containsKey(name)) {
			return threadLocalScopes.get().get(name);
		} else {
			return null;
		}
	}

	public static void endScope(String name) {
		if (!initialized) {
			return;
		}
		if (threadLocalScopes.get().containsKey(name)) {
			threadLocalScopes.get().get(name).close();
		}
	}

	static void uninit() throws Exception {
		synchronized (metrics) {
			if (initialized) {
				MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
				if (mbs.isRegistered(oname)) {
					mbs.unregisterMBean(oname);
				}
				metrics.clear();
				initialized = false;
			}
		}
	}
}
