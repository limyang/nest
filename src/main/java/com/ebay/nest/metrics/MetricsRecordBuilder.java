package com.ebay.nest.metrics;

public abstract class MetricsRecordBuilder {

	  public abstract MetricsRecordBuilder tag(MetricsInfo info, String value);


	  public abstract MetricsRecordBuilder add(MetricsTag tag);


	  public abstract MetricsRecordBuilder add(AbstractMetric metric);


	  public abstract MetricsRecordBuilder setContext(String value);


	  public abstract MetricsRecordBuilder addCounter(MetricsInfo info, int value);


	  public abstract MetricsRecordBuilder addCounter(MetricsInfo info, long value);


	  public abstract MetricsRecordBuilder addGauge(MetricsInfo info, int value);


	  public abstract MetricsRecordBuilder addGauge(MetricsInfo info, long value);


	  public abstract MetricsRecordBuilder addGauge(MetricsInfo info, float value);


	  public abstract MetricsRecordBuilder addGauge(MetricsInfo info, double value);


	  public abstract MetricsCollector parent();


	  public MetricsCollector endRecord() { return parent(); }
	}