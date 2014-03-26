package com.ebay.nest.cascading.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import cascading.flow.FlowProcess;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.ebay.nest.TypeInfo;
import com.ebay.nest.AttributeUtil;
import com.ebay.nest.TableSchema_remove;

public class UpdateOp extends ScriptOperation implements
		Function<ScriptOperation.Context> {

	private static final long serialVersionUID = -8487910400801555927L;
	private Map<Fields, Fields> setPairFields = new HashMap<Fields, Fields>();
	private TableSchema_remove schema;

	public UpdateOp(Fields fieldDeclaration, Fields masterFields,
			Fields slaveFields, String block, TableSchema_remove schema) {
		super(fieldDeclaration.size(), fieldDeclaration, block, Boolean.class);
		this.schema = schema;
		int ml = masterFields.size();
		int sl = slaveFields.size();
		Set<String> set = new TreeSet<String>();
		if (ml != sl) {
			assert (false);
		}
		for (int i = 0; i < masterFields.size(); i++) {
			String masterName = (String) masterFields.get(i);
			Class masterType = (Class) masterFields.getType(i);
			String slaveName = (String) slaveFields.get(i);
			Class slaveType = (Class) slaveFields.getType(i);
			Fields mf = new Fields(masterName, masterType);
			Fields sf = new Fields(slaveName, slaveType);
			if (set.contains(masterName)) {
				continue;
			}
			set.add(masterName);
			setPairFields.put(mf, sf);
		}

	}

	public void operate(FlowProcess flowProcess,
			FunctionCall<Context> functionCall) {
		TupleEntry entry = functionCall.getArguments();
		TupleEntry resultEntry = new TupleEntry(entry);
		Boolean b = (Boolean) evaluate(functionCall.getContext(),
				functionCall.getArguments());
		if (!b) {
			functionCall.getOutputCollector().add(resultEntry);
			return;
		}

		for (Fields masterFields : setPairFields.keySet()) {

			String masterName = (String) masterFields.get(0);

			Fields slaveFields = setPairFields.get(masterFields);
			Comparable slaveName = slaveFields.get(0);
			Object slaveObject = resultEntry.getObject(slaveName);

			if (slaveObject != null) {
				int index = masterName.lastIndexOf('$');
				String ms = masterName;
				if (index != -1) {
					ms = masterName.substring(index + 1);
				}
				TypeInfo attr = schema.getAttributeForCol(ms);
				slaveObject = AttributeUtil.castTo(attr, slaveObject);
				resultEntry.setObject(masterName, slaveObject);
			}
		}
		functionCall.getOutputCollector().add(resultEntry);

	}

}
