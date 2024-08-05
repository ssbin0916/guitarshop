package com.project.guitarshop.board.exception;

public class NotFoundPostException extends RuntimeException {

    public NotFoundPostException() {
    }

    public NotFoundPostException(String message) {
        super(message);
    }

    public NotFoundPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundPostException(Throwable cause) {
        super(cause);
    }
}

