package com.example.bookingsystem.exception;

public class ServiceItemNotFoundException extends RuntimeException {
    public ServiceItemNotFoundException(String message) {
        super(message);
    }
}
