package com.ebay.nest.cascading.operation;

import java.util.List;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.FieldsResolverException;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;

import com.ebay.nest.TypeInfo;
import com.ebay.nest.AttributeUtil;
import com.ebay.nest.Column;
import com.ebay.nest.TableSchema_remove;

public class InsertOP extends BaseOperation implements Function {

	private static final long serialVersionUID = 473240249974042171L;
	private final TableSchema_remove schema;

	public InsertOP(Fields fieldDeclaration, TableSchema_remove schema) {
		super(ANY, fieldDeclaration);
		this.schema = schema;
	}

	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		TupleEntry entry = functionCall.getArguments();
		TupleEntry resultEntry = new TupleEntry(entry);
		List<Column> fsl = schema.getCols();
		Tuple result = new Tuple();
		for (Column fs : fsl) {
			String col = fs.getName();
			Object o;
			try {
				o = resultEntry.getObject(col);
			} catch (FieldsResolverException e) {
				o = null;
			}
			TypeInfo attr = schema.getAttributeForCol(col);
			if (o == null) {
				result.add(null);
				continue;
			}
			o = AttributeUtil.castTo(attr, o);
			result.add(o);
			;
		}
		functionCall.getOutputCollector().add(result);
	}

}
