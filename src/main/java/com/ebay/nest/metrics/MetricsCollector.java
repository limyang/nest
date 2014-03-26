package com.ebay.nest.metrics;

public interface MetricsCollector {

	public MetricsRecordBuilder addRecord(String name);

	public MetricsRecordBuilder addRecord(MetricsInfo info);
}