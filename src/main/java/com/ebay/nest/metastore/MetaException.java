package com.ebay.nest.metastore;

public class MetaException extends Exception {
	private static final long serialVersionUID = -3961036701639162134L;
	private String message;

	public MetaException() {
	}

	public MetaException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}