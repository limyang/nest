package com.ebay.nest._remove.method;

import java.util.Map;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;

import com.ebay.nest.NestContext;
import com.ebay.nest._remove.ddi.DDiTable;

public abstract class Method {

	protected NestContext context;
	protected ParameterHelper params;

	public Method(NestContext context, Map<String, Object> params) {
		this.context = context;
		this.params = new ParameterHelper(params);
	}

	public void setResult(String pipeName, Pipe pipe, Fields fields,
			Pipe... sourcePipes) {
		context.setPipe(pipeName, pipe);
		context.setSinkFields(pipeName, fields);
		for (Pipe sourcePipe : sourcePipes)
			context.addPipeSource(pipe, sourcePipe.getName());
	}

	protected Pipe getPipe(DDiTable table) {
		return context.getPipeForName(table.tagName());
	}

	public String source() {
		return params.s("stg");
	}

	public String target() {
		return params.s("target");
	}

	public String base() {
		return params.s("base");
	}

	public Pipe basePipe() {
		return context.getPipeForName(base());
	}

	public Pipe sourcePipe() {
		return context.getPipeForName(source());
	}

	public Pipe targetPipe() {
		return context.getPipeForName(target());
	}

	public abstract void invoke() throws Exception;
}
