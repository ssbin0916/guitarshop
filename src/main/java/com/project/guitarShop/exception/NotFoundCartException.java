package com.project.guitarShop.exception;

public class NotFoundCartException extends RuntimeException {

    public NotFoundCartException() {
    }

    public NotFoundCartException(String message) {
        super(message);
    }

    public NotFoundCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCartException(Throwable cause) {
        super(cause);
    }
}
