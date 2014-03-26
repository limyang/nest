package com.ebay.nest.plan;

import com.ebay.nest.NestException;

public class PlanException extends NestException {

	private static final long serialVersionUID = 1L;

	public PlanException() {
		super();
	}

	public PlanException(String message) {
		super(message);
	}

	public PlanException(Throwable cause) {
		super(cause);
	}

	public PlanException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlanException(String message, int errCode) {
		super(message, errCode);
	}

	public PlanException(String message, int errCode, Throwable cause) {
		super(message, errCode, cause);
	}

	public PlanException(String message, int errCode, byte errSrc) {
		super(message, errCode, errSrc);
	}

	public PlanException(String message, int errCode, byte errSrc, Throwable cause) {
		super(message, errCode, errSrc, cause);
	}

	public PlanException(String message, int errCode, boolean retry) {
		super(message, errCode, retry);
	}

	public PlanException(String message, int errCode, byte errSrc, boolean retry) {
		super(message, errCode, errSrc, retry);
	}

	public PlanException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg) {
		super(message, errCode, errSrc, retry, detailedMsg);
	}

	public PlanException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg, Throwable cause) {
		super(message, errCode, errSrc, retry, detailedMsg, cause);
	}
}
