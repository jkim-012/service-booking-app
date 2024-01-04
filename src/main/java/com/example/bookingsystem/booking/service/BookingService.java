package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    BookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto);
    BookingDetailDto updateBookingDetails(Long bookingId, UpdateBookingDto updateBookingDto);
    BookingDetailDto updateBookingStatus(Long bookingId, BookingStatus newStatus);
    BookingDetailDto getBookingDetails(Long bookingId);
    Page<Booking> getAllBookingByBusiness(Long businessId, Pageable pageable);
}
