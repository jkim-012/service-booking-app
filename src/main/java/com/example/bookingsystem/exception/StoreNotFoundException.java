package com.example.bookingsystem.exception;

public class StoreNotFoundException extends RuntimeException{
  public StoreNotFoundException(String message) {
    super(message);
  }
}
