package com.ebay.nest.plan;

import com.ebay.nest.NestException;

public class VisitorException extends NestException {

	private static final long serialVersionUID = 1L;

	public VisitorException() {
		super();
	}

	public VisitorException(String message) {
		super(message);
	}

	public VisitorException(Throwable cause) {
		super(cause);
	}

	public VisitorException(String message, Throwable cause) {
		super(message, cause);
	}

	public VisitorException(String message, int errCode) {
		super(message, errCode);
	}

	public VisitorException(String message, int errCode, Throwable cause) {
		super(message, errCode, cause);
	}

	public VisitorException(String message, int errCode, byte errSrc) {
		super(message, errCode, errSrc);
	}

	public VisitorException(String message, int errCode, byte errSrc, Throwable cause) {
		super(message, errCode, errSrc, cause);
	}

	public VisitorException(String message, int errCode, boolean retry) {
		super(message, errCode, retry);
	}

	public VisitorException(String message, int errCode, byte errSrc, boolean retry) {
		super(message, errCode, errSrc, retry);
	}

	public VisitorException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg) {
		super(message, errCode, errSrc, retry, detailedMsg);
	}

	public VisitorException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg, Throwable cause) {
		super(message, errCode, errSrc, retry, detailedMsg, cause);
	}

}
