package com.example.bookingsystem.booking.service;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.customer.CustomerBookingDetailDto;
import com.example.bookingsystem.booking.dto.customer.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerBookingService {
    CustomerBookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto);
    CustomerBookingDetailDto updateBookingByCustomer(Long bookingId, UpdateBookingDto updateBookingDto);
    CustomerBookingDetailDto updateStatusByCustomer(Long bookingId, BookingStatus newStatus);
    CustomerBookingDetailDto getBookingByCustomer(Long bookingId);
    Page<Booking> getAllBookingsForCustomer(Pageable pageable);
}
