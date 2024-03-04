package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.booking.dto.business.BusinessBookingDetailDto;
import com.example.bookingsystem.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessBookingService {
    BusinessBookingDetailDto updateBookingByBusiness(Long bookingId, UpdateBookingDto updateBookingDto, Member member);
    BusinessBookingDetailDto updateStatusByBusiness(Long bookingId, BookingStatus newStatus, Member member);
    BusinessBookingDetailDto getBookingForBusiness(Long bookingId, Member member);
    Page<Booking> getAllBookingsForBusiness(Long businessId, Pageable pageable, Member member);
}
