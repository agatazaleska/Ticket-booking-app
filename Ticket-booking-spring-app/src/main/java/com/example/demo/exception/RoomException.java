package com.example.demo.exception;

public class RoomException extends RuntimeException {
    public RoomException(String message) {
        super(message);
    }

    public RoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomException(Throwable cause) {
        super(cause);
    }
}