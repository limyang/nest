package com.ebay.nest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class NestLogger {
	private static NestLogger instance = new NestLogger();

	public static synchronized NestLogger getInstance() {
		if (instance == null) {
			instance = new NestLogger();
		}
		return instance;
	}

	private static Log log = LogFactory.getLog(NestLogger.class);

	private NestStatusReporter reporter = null;

	private boolean aggregate = false;

	private NestLogger() {
	}

	public void warn(Object o, String msg, Enum warningEnum) {
		String displayMessage = o.getClass().getName() + ": " + msg;

		if (getAggregate()) {
			if (reporter != null) {
				reporter.getCounter(warningEnum).increment(1);
			} else {
				log.warn(displayMessage);
			}
		} else {
			log.warn(displayMessage);
		}
	}

	public synchronized void setReporter(NestStatusReporter rep) {
		this.reporter = rep;
	}

	public synchronized boolean getAggregate() {
		return aggregate;
	}

	public synchronized void setAggregate(boolean aggregate) {
		this.aggregate = aggregate;
	}

}
