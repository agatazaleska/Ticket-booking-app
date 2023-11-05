package com.example.demo.exception;

public class ScreeningException extends RuntimeException {
    public ScreeningException(String message) {
        super(message);
    }

    public ScreeningException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreeningException(Throwable cause) {
        super(cause);
    }
}
