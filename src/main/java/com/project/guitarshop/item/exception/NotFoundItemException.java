package com.project.guitarshop.item.exception;

public class NotFoundItemException extends RuntimeException {

    public NotFoundItemException() {
    }

    public NotFoundItemException(String message) {
        super(message);
    }

    public NotFoundItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundItemException(Throwable cause) {
        super(cause);
    }
}

