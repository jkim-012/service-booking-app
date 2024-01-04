package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;

public interface BookingService {
    BookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto);
    BookingDetailDto updateBooking(Long bookingId, UpdateBookingDto updateBookingDto);
    BookingDetailDto updateBookingStatus(Long bookingId, BookingStatus newStatus);
    BookingDetailDto getBookingDetails(Long bookingId);
}
