package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.customer.CustomerBookingDetailDto;
import com.example.bookingsystem.booking.dto.customer.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerBookingService {
    CustomerBookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto, Member member);
    CustomerBookingDetailDto updateBookingByCustomer(Long bookingId, UpdateBookingDto updateBookingDto, Member member);
    CustomerBookingDetailDto updateStatusByCustomer(Long bookingId, BookingStatus newStatus, Member member);
    CustomerBookingDetailDto getBookingByCustomer(Long bookingId, Member member);
    Page<Booking> getAllBookingsForCustomer(Pageable pageable, Member member);
}
