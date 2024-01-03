package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;

public interface BookingService {
    BookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto);
}
