package com.example.bookingsystem.booking.dto;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.member.dto.MemberDetailDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailDto {

    private Long bookingId;
    private LocalDateTime createdAt;
    private LocalDateTime scheduledAt;
    private LocalDateTime updatedAt;
    private BookingStatus status;
    private String memo; //additional information
    private ServiceItemDetailDto serviceItem;
    private BusinessDetailDto business;
    private MemberDetailDto member;


    // Booking entity -> BookingDetailDto
    public static BookingDetailDto of(Booking booking) {
        return BookingDetailDto.builder()
                .bookingId(booking.getId())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .scheduledAt(booking.getScheduledAt())
                .status(booking.getStatus())
                .memo(booking.getMemo())
                .serviceItem(ServiceItemDetailDto.of(booking.getServiceItem()))
                .business(BusinessDetailDto.of(booking.getBusiness()))
                .member(MemberDetailDto.of(booking.getMember()))
                .build();
    }
}
