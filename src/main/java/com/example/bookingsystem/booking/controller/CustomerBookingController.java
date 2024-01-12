package com.example.bookingsystem.booking.controller;


import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.customer.CustomerBookingDetailDto;
import com.example.bookingsystem.booking.dto.customer.CustomerBookingListDto;
import com.example.bookingsystem.booking.dto.customer.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.booking.service.CustomerBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class CustomerBookingController {

    private final CustomerBookingService customerService;

    // API endpoint for booking an appointment
    @PostMapping("/customer/bookings/services/{serviceId}")
    public ResponseEntity<CustomerBookingDetailDto> createBooking(
            @PathVariable Long serviceId,
            @RequestBody NewBookingDto newBookingDto){

        CustomerBookingDetailDto customerBookingDetailDto =
                customerService.createBooking(serviceId, newBookingDto);
        return ResponseEntity.ok(customerBookingDetailDto);
    }


    // API endpoint for updating a booking schedule or memo by customer
    @PutMapping("/customer/bookings/{bookingId}")
    public ResponseEntity<CustomerBookingDetailDto> updateBookingByCustomer(
            @PathVariable Long bookingId,
            @RequestBody UpdateBookingDto updateBookingDto){

        CustomerBookingDetailDto customerBookingDetailDto =
                customerService.updateBookingByCustomer(bookingId, updateBookingDto);
        return ResponseEntity.ok(customerBookingDetailDto);
    }

    // API endpoint for updating booking status by customer (cancel, complete)
    @PatchMapping("/customer/bookings/{bookingId}/status/{newStatus}")
    public ResponseEntity<CustomerBookingDetailDto> updateBookingStatusByCustomer(
            @PathVariable Long bookingId,
            @PathVariable BookingStatus newStatus){

        CustomerBookingDetailDto customerBookingDetailDto =
                customerService.updateStatusByCustomer(bookingId, newStatus);
        return ResponseEntity.ok(customerBookingDetailDto);
    }

    // API endpoint for reading booking details by customer
    @GetMapping("/customer/bookings/{bookingId}")
    public ResponseEntity<CustomerBookingDetailDto> getBookingDetailsForCustomer(
            @PathVariable Long bookingId){

        CustomerBookingDetailDto customerBookingDetailDto =
                customerService.getBookingByCustomer(bookingId);
        return ResponseEntity.ok(customerBookingDetailDto);
    }

    // API endpoint for reading booking list for a customer
    @GetMapping("/customer/bookings/list")
    public ResponseEntity<CustomerBookingListDto> getAllBookingsForCustomer(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "scheduledAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder){

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Booking> result = customerService.getAllBookingsForCustomer(pageable);
        return ResponseEntity.ok(CustomerBookingListDto.of(result));
    }



}
