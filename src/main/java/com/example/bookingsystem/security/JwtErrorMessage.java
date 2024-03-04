package com.example.bookingsystem.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Getter
public enum JwtErrorMessage {

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired JWT", LocalDateTime.now().toString()),
    MALFORMED_TOKEN(HttpStatus.BAD_REQUEST, "Malformed JWT", LocalDateTime.now().toString()),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown JWT error", LocalDateTime.now().toString());

    private final HttpStatus httpStatus;
    private final String message;
    private final String timestamp;
}
