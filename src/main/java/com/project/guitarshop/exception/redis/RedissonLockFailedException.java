package com.project.guitarshop.exception.redis;

public class RedissonLockFailedException extends RuntimeException {

    public RedissonLockFailedException() {
        super();
    }

    public RedissonLockFailedException(String message) {
        super(message);
    }

    public RedissonLockFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedissonLockFailedException(Throwable cause) {
        super(cause);
    }
}
