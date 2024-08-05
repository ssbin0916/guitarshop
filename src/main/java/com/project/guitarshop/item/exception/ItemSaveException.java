package com.project.guitarshop.item.exception;

public class ItemSaveException extends RuntimeException {

    public ItemSaveException() {
    }

    public ItemSaveException(String message) {
        super(message);
    }

    public ItemSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemSaveException(Throwable cause) {
        super(cause);
    }
}

