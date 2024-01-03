package com.example.bookingsystem.booking.controller;


import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/booking")
@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    // API endpoint for booking an appointment
    @PostMapping("/service/{serviceId}")
    public ResponseEntity<BookingDetailDto> createBooking(
            @PathVariable Long serviceId,
            @RequestBody NewBookingDto newBookingDto){

        BookingDetailDto bookingDetailDto = bookingService.createBooking(serviceId, newBookingDto);
        return ResponseEntity.ok(bookingDetailDto);
    }



}
