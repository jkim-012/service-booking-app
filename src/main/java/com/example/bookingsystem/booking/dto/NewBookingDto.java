package com.example.bookingsystem.booking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewBookingDto {

    private LocalDateTime scheduledAt;
    private String memo; //additional information
}
