package com.bookmarks.bookmarksstore.rest.exceptions;

import com.bookmarks.bookmarksstore.service.exceptions.RecordDoesNotExistException;

public class RecordNotFoundException extends ApiException {
    private static final long serialVersionUID = -8928433347702090662L;

    public RecordNotFoundException(RecordDoesNotExistException e){
        super(e.getMessage(),e);
    }
}
