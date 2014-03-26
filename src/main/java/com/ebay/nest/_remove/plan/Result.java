package com.ebay.nest._remove.plan;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte returnStatus;

	public Object result;

	public Result() {
		returnStatus = POStatus.STATUS_ERR;
		result = null;
	}

	public Result(byte returnStatus, Object result) {
		this.returnStatus = returnStatus;
		this.result = result;
	}


	public String toString() {
		return (result != null) ? result.toString() : "NULL";
	}

}