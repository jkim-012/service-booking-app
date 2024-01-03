package com.example.bookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<String> handleBusinessNotFoundException(BusinessNotFoundException e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleEmailAlreadyExistException(EmailAlreadyExistException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleNotBusinessOwnerException(NotBusinessOwnerException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleServiceItemNotFoundException(ServiceItemNotFoundException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
}
