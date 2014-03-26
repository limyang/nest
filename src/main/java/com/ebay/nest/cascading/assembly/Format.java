package com.ebay.nest.cascading.assembly;

import cascading.operation.regex.RegexSplitter;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.tuple.Fields;

public class Format extends SubAssembly {

	private static final long serialVersionUID = 8530984207878768194L;

	public Format(String pipeName, String delimiter, Fields declaredFields) {
		this(pipeName, new Fields("line"), delimiter, declaredFields);
	}

	public Format(String pipeName, Fields argumentSelector, String delimiter,
			Fields declaredFields) {

		RegexSplitter regexSplitter = new RegexSplitter(declaredFields,
				delimiter);
		Pipe pipe = new Each(pipeName, argumentSelector, regexSplitter,
				declaredFields);

		setTails(pipe);
	}
}
