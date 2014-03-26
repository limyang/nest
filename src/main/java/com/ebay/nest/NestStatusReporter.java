package com.ebay.nest;

import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.TaskInputOutputContext;
import org.apache.hadoop.util.Progressable;

public class NestStatusReporter extends org.apache.hadoop.mapreduce.StatusReporter implements Progressable {

	private TaskInputOutputContext context;
	private static NestStatusReporter reporter = null;

	public static NestStatusReporter getInstance() {
		if (reporter == null) {
			reporter = new NestStatusReporter(null);
		}
		return reporter;
	}

	public static void setContext(TaskInputOutputContext context) {
		reporter = new NestStatusReporter(context);
	}

	private NestStatusReporter(TaskInputOutputContext context) {
		this.context = context;
	}

	@Override
	public Counter getCounter(Enum<?> name) {
		return (context == null) ? null : context.getCounter(name);
	}

	@Override
	public Counter getCounter(String group, String name) {
		return (context == null) ? null : context.getCounter(group, name);
	}

	@Override
	public void progress() {
		if (context != null) {
			context.progress();
		}
	}

	@Override
	public void setStatus(String status) {
		if (context != null) {
			context.setStatus(status);
		}
	}

	public float getProgress() {
		return 0;
	}
}
