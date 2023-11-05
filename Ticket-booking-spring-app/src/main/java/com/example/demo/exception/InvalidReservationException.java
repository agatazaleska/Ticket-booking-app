package com.example.demo.exception;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException(String message) {
        super(message);
    }

    public InvalidReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationException(Throwable cause) {
        super(cause);
    }
}