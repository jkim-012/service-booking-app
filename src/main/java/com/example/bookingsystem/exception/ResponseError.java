package com.example.bookingsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseError {

  private String field;
  private String message;

  // error -> ResponseError
  public static ResponseError of(FieldError e) {
    return ResponseError.builder()
        .message(e.getDefaultMessage())
        .field(((FieldError) e).getField())
        .build();
  }
}