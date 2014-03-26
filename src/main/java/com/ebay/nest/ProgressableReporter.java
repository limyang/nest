package com.ebay.nest;

import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class ProgressableReporter implements NestProgressable {
	TaskAttemptContext rep;

	public ProgressableReporter() {

	}

	public ProgressableReporter(TaskAttemptContext rep) {
		super();
		this.rep = rep;
	}

	public void progress() {
		if (rep != null)
			rep.progress();
	}

	public void progress(String msg) {
		try {
			rep.setStatus(msg);
		} catch (Exception e) {
			rep.progress();
		}
	}

	public void setRep(TaskAttemptContext rep) {
		this.rep = rep;
	}

}