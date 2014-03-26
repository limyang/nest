package com.ebay.nest;

import java.io.IOException;

public class NestException extends IOException {

	static final long serialVersionUID = 1L;

	public static final byte INPUT = 2;
	public static final byte BUG = 4;
	public static final byte USER_ENVIRONMENT = 8;
	public static final byte REMOTE_ENVIRONMENT = 16;
	public static final byte ERROR = -1;

	public static boolean isInput(byte errSource) {
		return ((errSource & INPUT) == 0 ? false : true);
	}

	public static boolean isBug(byte errSource) {
		return ((errSource & BUG) == 0 ? false : true);
	}

	public static boolean isUserEnvironment(byte errSource) {
		return ((errSource & USER_ENVIRONMENT) == 0 ? false : true);
	}

	public static boolean isRemoteEnvironment(byte errSource) {
		return ((errSource & REMOTE_ENVIRONMENT) == 0 ? false : true);
	}

	public static byte determineErrorSource(int errCode) {
		if (errCode >= 100 && errCode <= 1999) {
			return NestException.INPUT;
		} else if (errCode >= 2000 && errCode <= 2999) {
			return NestException.BUG;
		} else if (errCode >= 3000 && errCode <= 4999) {
			return NestException.USER_ENVIRONMENT;
		} else if (errCode >= 5000 && errCode <= 6999) {
			return NestException.REMOTE_ENVIRONMENT;
		}
		return NestException.ERROR;
	}

	protected int errorCode = 0;
	protected byte errorSource = BUG;
	protected boolean retriable = false;
	protected String detailedMessage = null;
	protected boolean markedAsShowToUser = false;
	protected SourceLocation sourceLocation = null;

	public NestException() {
		super();
	}

	public NestException(String message) {
		super(message);
	}

	public NestException(String message, SourceLocation location) {
		super(message);
		sourceLocation = location;
	}

	public NestException(Throwable cause) {
		super(cause);
	}

	public NestException(String message, Throwable cause) {
		super(message, cause);
	}

	public NestException(String message, int errCode) {
		this(message);
		errorCode = errCode;
	}

	public NestException(String message, int errCode, SourceLocation location) {
		this(message, location);
		errorCode = errCode;
	}

	public NestException(String message, int errCode, Throwable cause) {
		this(message, cause);
		errorCode = errCode;
	}

	public NestException(String message, int errCode, Throwable cause, SourceLocation location) {
		this(message, cause);
		errorCode = errCode;
		sourceLocation = location;
	}

	public NestException(String message, int errCode, byte errSrc) {
		this(message, errCode);
		errorSource = errSrc;
	}

	public NestException(String message, int errCode, byte errSrc, SourceLocation location) {
		this(message, errCode, location);
		errorSource = errSrc;
	}

	public NestException(String message, int errCode, byte errSrc, Throwable cause) {
		this(message, errCode, errSrc, false, null, cause);
	}

	public NestException(String message, int errCode, byte errSrc, Throwable cause, SourceLocation location) {
		this(message, errCode, errSrc, false, null, cause, location);
	}

	public NestException(String message, int errCode, boolean retry) {
		this(message, errCode);
		retriable = retry;
	}

	public NestException(String message, int errCode, byte errSrc, boolean retry) {
		this(message, errCode, errSrc);
		retriable = retry;
	}

	public NestException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg) {
		this(message, errCode, errSrc, retry);
		detailedMessage = detailedMsg;
	}

	public NestException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg,
			SourceLocation location) {
		this(message, errCode, errSrc, retry);
		detailedMessage = detailedMsg;
		sourceLocation = location;
	}

	public NestException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg, Throwable cause) {
		super(message, cause);
		errorCode = errCode;
		errorSource = errSrc;
		retriable = retry;
		detailedMessage = detailedMsg;
	}

	public NestException(String message, int errCode, byte errSrc, boolean retry, String detailedMsg, Throwable cause,
			SourceLocation location) {
		super(message, cause);
		errorCode = errCode;
		errorSource = errSrc;
		retriable = retry;
		detailedMessage = detailedMsg;
		sourceLocation = location;
	}

	public boolean retriable() {
		return retriable;
	}

	public void setRetriable(boolean retry) {
		retriable = retry;
	}

	public byte getErrorSource() {
		return errorSource;
	}

	public void setErrorSource(byte src) {
		errorSource = src;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int code) {
		errorCode = code;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

	public void setDetailedMessage(String detailMsg) {
		detailedMessage = detailMsg;
	}

	public boolean getMarkedAsShowToUser() {
		return markedAsShowToUser;
	}

	public void setMarkedAsShowToUser(boolean showToUser) {
		markedAsShowToUser = showToUser;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation location) {
		sourceLocation = location;
	}

	public String toString() {
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return (message != null) ? (s + ": " + "ERROR " + getErrorCode() + ": " + message) : s;
	}
}
