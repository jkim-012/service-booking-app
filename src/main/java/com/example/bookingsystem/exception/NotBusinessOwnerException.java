package com.example.bookingsystem.exception;

public class NotBusinessOwnerException extends RuntimeException {
    public NotBusinessOwnerException(String message) {
        super(message);
    }
}
