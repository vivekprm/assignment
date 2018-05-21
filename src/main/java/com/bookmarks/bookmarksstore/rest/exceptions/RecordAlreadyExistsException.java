package com.bookmarks.bookmarksstore.rest.exceptions;

import com.bookmarks.bookmarksstore.service.exceptions.RecordExistsException;

public class RecordAlreadyExistsException extends ApiException {

	public RecordAlreadyExistsException(RecordExistsException e){
		super(e.getMessage(),e);
	}
}
