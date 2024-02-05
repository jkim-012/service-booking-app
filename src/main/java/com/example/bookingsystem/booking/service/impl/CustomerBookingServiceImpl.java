package com.example.bookingsystem.booking.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.customer.CustomerBookingDetailDto;
import com.example.bookingsystem.booking.dto.customer.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.booking.service.CustomerBookingService;
import com.example.bookingsystem.exception.*;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.domain.Role;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.repository.ServiceItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerBookingServiceImpl implements CustomerBookingService {

    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final ServiceItemRepository serviceItemRepository;

    @Override
    public CustomerBookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto) {

        // check the booking date is later than current time
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime bookingTime = newBookingDto.getScheduledAt();

        if (bookingTime.isBefore(currentTime)){
            throw new BookingNotAvailableException("Booking date must be later than the current time.");
        }
        // get logged in member
        Member member = getLoggedInMember();
        if (!member.getRole().equals(Role.CUSTOMER)) {
            throw new NotCustomerException("Booking is only allowed for customers.");
        }
        // find the service
        ServiceItem serviceItem = getServiceItemById(serviceId);

        // check if booking is available
        Optional<Booking> byScheduledAt = bookingRepository.findByScheduledAt(newBookingDto.getScheduledAt());
        if (byScheduledAt.isPresent()) {
            throw new BookingNotAvailableException("The specified time slot is already booked.");
        }
        // book an appointment
        Booking booking = Booking.builder()
                .scheduledAt(newBookingDto.getScheduledAt())
                .memo(newBookingDto.getMemo())
                .status(BookingStatus.BOOKED)
                .serviceItem(serviceItem)
                .business(serviceItem.getBusiness())
                .member(member)
                .build();

        bookingRepository.save(booking);
        return CustomerBookingDetailDto.of(booking);
    }

    @Override
    @Transactional
    public CustomerBookingDetailDto updateBookingByCustomer(
            Long bookingId, UpdateBookingDto updateBookingDto) {

        // get logged in member
        Member member = getLoggedInMember();

        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority (logged in member should be the owner of the booking)
        if (!member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking");
        }
        // update
        booking.changeBookingInfo(updateBookingDto);
        return CustomerBookingDetailDto.of(booking);
    }

    @Override
    @Transactional
    public CustomerBookingDetailDto updateStatusByCustomer(Long bookingId, BookingStatus newStatus) {

        // get logged in member
        Member member = getLoggedInMember();
        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking status.");
        }
        // check the current status
        if (!booking.getStatus().equals(BookingStatus.BOOKED)) {
            throw new UnauthorizedUserException("Unauthorized: You can't change the status.: " + booking.getStatus());
        }
        // change status (cancel or complete)
        booking.changeStatus(newStatus);
        return CustomerBookingDetailDto.of(booking);
    }

    @Override
    public CustomerBookingDetailDto getBookingByCustomer(Long bookingId) {
        // get logged in member
        Member member = getLoggedInMember();
        // find the booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to read the booking details.");
        }
        return CustomerBookingDetailDto.of(booking);
    }


    @Override
    public Page<Booking> getAllBookingsForCustomer(Pageable pageable) {
        // get logged in member
        Member member = getLoggedInMember();
        // if business try to see the list, occur an exception
        if (member.getRole().equals(Role.BUSINESS)) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to read the list");
        }
        // get member id
        Long memberId = member.getId();
        // find all bookings for the member
        Page<Booking> bookings = bookingRepository.findAllByMemberId(memberId, pageable);
        // if there is no booking
        if (bookings.isEmpty()){
            throw new BookingNotFoundException("There is no booking.");
        }
        return bookings;
    }

    private Member getLoggedInMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return member;
    }

    private ServiceItem getServiceItemById(Long serviceId) {
        ServiceItem serviceItem = serviceItemRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceItemNotFoundException("Service not found with ID: " + serviceId));
        return serviceItem;
    }

}
