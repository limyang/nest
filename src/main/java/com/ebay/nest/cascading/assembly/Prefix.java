package com.ebay.nest.cascading.assembly;

import com.ebay.nest.utils.FieldsUtil;

import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.pipe.assembly.Rename;
import cascading.tuple.Fields;

public class Prefix extends SubAssembly {

	private static final long serialVersionUID = 925661171386245062L;

	public Prefix(Pipe previous, String prefix, Fields originFields) {
		Pipe pipe = previous;
		Fields newFields = FieldsUtil.prefix(originFields, prefix);
		pipe = new Rename(pipe, originFields, newFields);
		setTails(pipe);
	}
}
