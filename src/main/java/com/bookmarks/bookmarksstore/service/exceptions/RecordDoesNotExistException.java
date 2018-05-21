package com.bookmarks.bookmarksstore.service.exceptions;

public class RecordDoesNotExistException extends ServiceException {

    private static final long serialVersionUID = 2536181525886249209L;

    public RecordDoesNotExistException(String message, Throwable cause){
        super(message, cause);
    }
}
