package com.project.guitarShop.exception;

public class NotFoundMemberException extends RuntimeException {

    public NotFoundMemberException() {
    }

    public NotFoundMemberException(String message) {
        super(message);
    }

    public NotFoundMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMemberException(Throwable cause) {
        super(cause);
    }
}

