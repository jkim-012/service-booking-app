package com.example.bookingsystem.booking.controller;


import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
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


    // API endpoint for updating a booking schedule or memo
    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDetailDto> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody UpdateBookingDto updateBookingDto){
        BookingDetailDto bookingDetailDto = bookingService.updateBooking(bookingId, updateBookingDto);
        return ResponseEntity.ok(bookingDetailDto);
    }

    // API endpoint for updating booking status (cancel, complete)
    @PatchMapping("/booking/{bookingId}/status/{newStatus}")
    public ResponseEntity<BookingDetailDto> updateBookingStatus(
            @PathVariable Long bookingId,
            @PathVariable BookingStatus newStatus){
        BookingDetailDto bookingDetailDto = bookingService.updateBookingStatus(bookingId, newStatus);
        return ResponseEntity.ok(bookingDetailDto);
    }


}
