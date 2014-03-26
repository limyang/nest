/*
Copyright 2012 eBay Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.ebay.nest;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;

import cascading.flow.FlowProcess;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.scheme.hadoop.SequenceFile;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

public class FormattedSequenceFile extends SequenceFile {

	private static final long serialVersionUID = 625323417795766481L;

	public FormattedSequenceFile(Fields fields) {
		super(fields);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean source(FlowProcess<JobConf> flowProcess,
			SourceCall<Object[], RecordReader> sourceCall) throws IOException {
		Object keyObject = sourceCall.getContext()[0];
		Text valueObject = (Text) sourceCall.getContext()[1];

		Tuple tuple = null;
		if (sourceCall.getInput().next(keyObject, valueObject)) {
			tuple = sourceCall.getIncomingEntry().getTuple();
			tuple.clear();
			String valueString = valueObject.toString();
			tuple.addAll(new String[] { valueString });
			return true;
		}
		return false;
	}

	public void sink(FlowProcess<JobConf> flowProcess,
			SinkCall<Void, OutputCollector> sinkCall) throws IOException {
		TupleEntry tupleEntry = sinkCall.getOutgoingEntry();
		Tuple tuple = tupleEntry.getTuple();
		Text key = new Text("");
		Text value = new Text(tuple.toString());
		sinkCall.getOutput().collect(key, value);
	}

	@Override
	public void sinkConfInit(FlowProcess<JobConf> flowProcess,
			Tap<JobConf, RecordReader, OutputCollector> tap, JobConf conf) {
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setOutputFormat(SequenceFileOutputFormat.class);
	}

}
