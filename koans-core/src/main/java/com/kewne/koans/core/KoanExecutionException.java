package com.kewne.koans.core;

public abstract class KoanExecutionException extends RuntimeException {

    public KoanExecutionException(String message) {
        super(message);
    }

    public KoanExecutionException(Throwable cause) {
        super(cause);
    }

    public KoanExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
