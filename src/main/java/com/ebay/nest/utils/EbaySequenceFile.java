package com.ebay.nest.utils;

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

public class EbaySequenceFile extends SequenceFile {

	private static final long serialVersionUID = 625323417795766481L;

	public EbaySequenceFile(Fields fields) {
		super(fields);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean source(FlowProcess<JobConf> flowProcess, SourceCall<Object[], RecordReader> sourceCall)
			throws IOException {
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

	public void sink(FlowProcess<JobConf> flowProcess, SinkCall<Void, OutputCollector> sinkCall) throws IOException {
		TupleEntry tupleEntry = sinkCall.getOutgoingEntry();
		Tuple tuple = tupleEntry.getTuple();
		Text key = new Text("");
		Text value = new Text(tuple.toString());
		sinkCall.getOutput().collect(key, value);
	}

	@Override
	public void sinkConfInit(FlowProcess<JobConf> flowProcess, Tap<JobConf, RecordReader, OutputCollector> tap,
			JobConf conf) {
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		conf.setOutputFormat(SequenceFileOutputFormat.class);
	}
}
