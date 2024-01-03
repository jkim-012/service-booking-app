package com.example.bookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessNotFoundException.class)
  public ResponseEntity<String> handleBusinessNotFoundException(BusinessNotFoundException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(EmailAlreadyExistException.class)
  public ResponseEntity<String> handleEmailAlreadyExistException(EmailAlreadyExistException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(NotBusinessOwnerException.class)
  public ResponseEntity<String> handleNotBusinessOwnerException(NotBusinessOwnerException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  @ExceptionHandler(ServiceItemNotFoundException.class)
  public ResponseEntity<String> handleServiceItemNotFoundException(ServiceItemNotFoundException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(NotCustomerException.class)
  public ResponseEntity<String> handleNotCustomerException(NotCustomerException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  @ExceptionHandler(BookingNotAvailableException.class)
  public ResponseEntity<String> handleBookingNotAvailableException(BookingNotAvailableException e){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }
}
