package com.bookmarks.bookmarksstore.rest.exceptions;

import java.io.IOException;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(IOException e) {
        super(e);
    }

    public ApiException(){

    }
}
