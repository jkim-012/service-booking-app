package com.example.bookingsystem.booking.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.booking.dto.business.BusinessBookingDetailDto;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.booking.service.BusinessBookingService;
import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.exception.BookingNotFoundException;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import com.example.bookingsystem.exception.ServiceItemNotFoundException;
import com.example.bookingsystem.exception.UnauthorizedUserException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.repository.ServiceItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BusinessBookingServiceImpl implements BusinessBookingService {

    private final BookingRepository bookingRepository;
    private final BusinessRepository businessRepository;

    @Override
    @Transactional
    public BusinessBookingDetailDto updateBookingByBusiness(Long bookingId, UpdateBookingDto updateBookingDto, Member member) {
        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority (logged in member should be the business owner of the booking)
        if (!member.getId().equals(booking.getBusiness().getMember().getId())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking");
        }
        // update
        booking.changeBookingInfo(updateBookingDto);
        return BusinessBookingDetailDto.of(booking);
    }

    @Override
    @Transactional
    public BusinessBookingDetailDto updateStatusByBusiness(Long bookingId, BookingStatus newStatus, Member member) {
        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));
        // check the member's authority
        if (!member.getId().equals(booking.getBusiness().getMember().getId())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking status.");
        }
        // check the current status
        if (!booking.getStatus().equals(BookingStatus.BOOKED)) {
            throw new UnauthorizedUserException("Unauthorized: You can't change the status.: " + booking.getStatus());
        }
        // change status (cancel or complete)
        booking.changeStatus(newStatus);
        return BusinessBookingDetailDto.of(booking);
    }

    @Override
    public BusinessBookingDetailDto getBookingForBusiness(Long bookingId, Member member) {
        // find the booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.getId().equals(booking.getBusiness().getMember().getId())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to read the booking details.");
        }
        return BusinessBookingDetailDto.of(booking);
    }

    @Override
    public Page<Booking> getAllBookingsForBusiness(Long businessId, Pageable pageable, Member member) {
        // find the business
        Business business = businessRepository.findById(businessId)
                .orElseThrow(()-> new BusinessNotFoundException("Business not found with ID: " + businessId));
        // check authorization (logged in member should be the business owner of the business)
        if (!business.getMember().getId().equals(member.getId())){
            throw new UnauthorizedUserException
                    ("Unauthorized: You do not have permission to read the booking list for this business.");
        }
        // find all bookings by businessId
        Page<Booking> bookings = bookingRepository.findAllByBusinessId(businessId, pageable);
        return bookings;
    }

}
