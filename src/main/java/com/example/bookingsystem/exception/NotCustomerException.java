package com.example.bookingsystem.exception;

public class NotCustomerException extends RuntimeException {
    public NotCustomerException(String message) {
        super(message);
    }
}
