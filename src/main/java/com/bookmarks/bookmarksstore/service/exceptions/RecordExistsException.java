package com.bookmarks.bookmarksstore.service.exceptions;

public class RecordExistsException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public RecordExistsException(String message, Throwable cause){
		super(message, cause);
	}
}
