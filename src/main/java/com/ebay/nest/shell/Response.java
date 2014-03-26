package com.ebay.nest.shell;

public class Response {
	private int responseCode;
	private String errorMessage;

	public Response(int responseCode) {
		this(responseCode, null);
	}

	public Response(int responseCode, String errorMessage) {
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;

	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
