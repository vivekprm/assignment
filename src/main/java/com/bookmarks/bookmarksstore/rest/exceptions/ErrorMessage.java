package com.bookmarks.bookmarksstore.rest.exceptions;

public class ErrorMessage {
	private String reason;

	public ErrorMessage(String reason) {
		this.reason = reason;
	}

	public ErrorMessage(ApiException ex) {
		this.reason = ex.getMessage();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
