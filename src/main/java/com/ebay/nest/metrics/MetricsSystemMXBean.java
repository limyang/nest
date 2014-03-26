package com.ebay.nest.metrics;

public interface MetricsSystemMXBean {

	public void start();

	public void stop();

	public void startMetricsMBeans();

	public void stopMetricsMBeans();

	public String currentConfig();
}
