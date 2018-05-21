package com.bookmarks.bookmarksstore.service.exceptions;

import java.io.IOException;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(IOException e) {
        super(e);
    }
}
