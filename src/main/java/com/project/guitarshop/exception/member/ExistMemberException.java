package com.project.guitarshop.exception.member;

public class ExistMemberException extends RuntimeException {

    public ExistMemberException() {
        super();
    }

    public ExistMemberException(String message) {
        super(message);
    }

    public ExistMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistMemberException(Throwable cause) {
        super(cause);
    }
}
