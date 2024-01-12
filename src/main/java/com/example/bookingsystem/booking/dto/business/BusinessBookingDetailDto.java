package com.example.bookingsystem.booking.dto.business;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.member.dto.MemberDetailDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessBookingDetailDto {

    private Long bookingId;
    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus status;
    private String memo; //additional information
    private ServiceItemDetailDto serviceItem;
    private MemberDetailDto member;


    // Booking entity -> BusinessBookingDetailDto
    public static BusinessBookingDetailDto of(Booking booking) {
        return BusinessBookingDetailDto.builder()
                .bookingId(booking.getId())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledAt(booking.getScheduledAt())
                .status(booking.getStatus())
                .memo(booking.getMemo())
                .serviceItem(ServiceItemDetailDto.of(booking.getServiceItem()))
                .member(MemberDetailDto.of(booking.getMember()))
                .build();
    }
}
