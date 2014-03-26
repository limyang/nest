package com.ebay.nest.analyzer;

import com.ebay.nest.NestException;

public class SemanticException extends NestException {

	private static final long serialVersionUID = 1L;

	public SemanticException() {
		super();
	}

	public SemanticException(String message) {
		super(message);
	}

	public SemanticException(Throwable cause) {
		super(cause);
	}

	public SemanticException(String message, Throwable cause) {
		super(message, cause);
	}

}