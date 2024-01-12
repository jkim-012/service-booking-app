package com.example.bookingsystem.booking.dto.customer;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBookingDetailDto {
    private Long bookingId;
    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus status;
    private String memo; //additional information
    private ServiceItemDetailDto serviceItem;
    private BusinessDetailDto business;


    // Booking entity -> BookingDetailDto
    public static CustomerBookingDetailDto of(Booking booking) {
        return CustomerBookingDetailDto.builder()
                .bookingId(booking.getId())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledAt(booking.getScheduledAt())
                .status(booking.getStatus())
                .memo(booking.getMemo())
                .serviceItem(ServiceItemDetailDto.of(booking.getServiceItem()))
                .business(BusinessDetailDto.of(booking.getBusiness()))
                .build();
    }
}
