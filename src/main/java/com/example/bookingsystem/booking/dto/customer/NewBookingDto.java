package com.example.bookingsystem.booking.dto.customer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewBookingDto {

    @NotNull(message = "Schedule date and time are required.")
    private LocalDateTime scheduledAt;
    private String memo; //additional information
}
