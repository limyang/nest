package com.ebay.nest.metrics;

import javax.management.DynamicMBean;

public interface MetricsMBean extends DynamicMBean {

	public abstract boolean hasKey(String name);

	public abstract void put(String name, Object value);

	public abstract Object get(String name);

	void clear();
}
