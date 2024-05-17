package com.project.guitarShop.exception.comment;

public class NotFoundCommentException extends RuntimeException {

    public NotFoundCommentException() {
    }

    public NotFoundCommentException(String message) {
        super(message);
    }

    public NotFoundCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCommentException(Throwable cause) {
        super(cause);
    }
}

