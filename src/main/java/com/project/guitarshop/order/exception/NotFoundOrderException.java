package com.project.guitarshop.order.exception;

public class NotFoundOrderException extends RuntimeException {

    public NotFoundOrderException() {
    }

    public NotFoundOrderException(String message) {
        super(message);
    }

    public NotFoundOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundOrderException(Throwable cause) {
        super(cause);
    }
}
