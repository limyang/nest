package com.ebay.nest.cascading.operation;

import java.sql.Timestamp;

import org.apache.commons.lang.ArrayUtils;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.ebay.nest._remove.ddi.Audit;
import com.ebay.nest.utils.FieldsUtil;

public abstract class AuditOperation extends BaseOperation implements Function {

	private static final long serialVersionUID = -6576363223525995066L;
	protected boolean isUpdate = false;
	protected boolean isCreate = false;
	protected Fields inputFields;

	public AuditOperation(Fields inputFields) {
		super(inputFields.size(), inputFields);
		this.inputFields = inputFields;
	}

	public AuditOperation() {
	}

	protected void setUpdateAudit(TupleEntry entry, String username) {
		setAuditTypeHelper(entry, username, Audit.UPDATE_DATE_COLUMNS, Audit.UPDATE_USER_COLUMNS);
	}

	protected void setCreateAudit(TupleEntry entry, String username) {
		setAuditTypeHelper(entry, username, Audit.CREATE_DATE_COLUMNS, Audit.CREATE_USER_COLUMNS);
	}

	private void setAuditTypeHelper(TupleEntry entry, String username, String[] dateColumns, String[] userColumns) {
		Fields fields = entry.getFields();
		String[] fieldNames = FieldsUtil.names(fields);
		for (String field : fieldNames) {
			if (ArrayUtils.contains(dateColumns, field)) {
				entry.setObject(field, new Timestamp(System.currentTimeMillis()));
			}

			if (ArrayUtils.contains(userColumns, field)) {
				entry.setObject(field, username);
			}
		}
	}

	protected void markUpdate() {
		this.isUpdate = true;
	}

	protected void markCreate() {
		this.isCreate = true;
	}

	private void restoreAuditFlag() {
		this.isUpdate = false;
		this.isCreate = false;
	}

	@Override
	public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
		restoreAuditFlag();
		String userName = flowProcess.getStringProperty("user.name");
		TupleEntry entry = functionCall.getArguments();
		TupleEntry resultEntry = new TupleEntry(entry);
		TupleEntry newResultEntry = process(resultEntry);

		if (newResultEntry == null) {
			return;
		}
		if (!newResultEntry.getFields().equals(resultEntry.getFields())) {
			System.out.println("Error : The result fields is different with origin fields!!!");
		}
		if (this.isCreate) {
			setCreateAudit(resultEntry, userName);
		}
		if (this.isUpdate) {
			setUpdateAudit(resultEntry, userName);
		}
		functionCall.getOutputCollector().add(resultEntry);
	}

	protected abstract TupleEntry process(TupleEntry entry);
}
