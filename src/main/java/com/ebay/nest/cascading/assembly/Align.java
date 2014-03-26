package com.ebay.nest.cascading.assembly;

import com.ebay.nest.utils.FieldsUtil;

import cascading.operation.Insert;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.tuple.Fields;

/**
 * Include adding columns and cast type
 * 
 * @author coolmore
 * 
 */
public class Align extends SubAssembly {

	private static final long serialVersionUID = 1280392270833394275L;

	public Align(Pipe previous, Fields sourceFields, Fields targetFields) {
		Pipe pipe = previous;
		Fields extraFields = FieldsUtil.getExtraFields(sourceFields, targetFields);
		int extraFieldsSize = extraFields.size();
		Object[] values = new Object[extraFieldsSize];
		for (int i = 0; i < extraFieldsSize; i++) {
			values[i] = null;
		}
		Insert insert = new Insert(extraFields, values);
		pipe = new Each(pipe, Fields.NONE, insert, targetFields);
		setTails(pipe);
	}
}
