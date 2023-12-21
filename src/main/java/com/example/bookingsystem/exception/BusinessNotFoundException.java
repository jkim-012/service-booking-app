package com.example.bookingsystem.exception;

public class BusinessNotFoundException extends RuntimeException{
  public BusinessNotFoundException(String message) {
    super(message);
  }
}
