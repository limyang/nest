package com.ebay.nest.metrics;

public interface MetricsSource {

	void getMetrics(MetricsCollector collector, boolean all);
}
