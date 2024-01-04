package com.example.bookingsystem.booking.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.booking.service.BookingService;
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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MemberRepository memberRepository;
    private final ServiceItemRepository serviceItemRepository;

    @Override
    public BookingDetailDto createBooking(Long serviceId, NewBookingDto newBookingDto) {

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
                .createdAt(LocalDateTime.now())
                .scheduledAt(newBookingDto.getScheduledAt())
                .memo(newBookingDto.getMemo())
                .status(BookingStatus.BOOKED)
                .serviceItem(serviceItem)
                .business(serviceItem.getBusiness())
                .member(member)
                .build();

        return BookingDetailDto.of(booking);
    }

    @Override
    public BookingDetailDto updateBookingDetails(Long bookingId, UpdateBookingDto updateBookingDto) {

        // get logged in member
        Member member = getLoggedInMember();

        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking");
        }
        // update
        booking.changeBookingInfo(updateBookingDto);
        return BookingDetailDto.of(booking);
    }

    @Override
    public BookingDetailDto updateBookingStatus(Long bookingId, BookingStatus newStatus) {

        // get logged in member
        Member member = getLoggedInMember();

        // find the booking to be updated
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.equals(booking.getBusiness().getMember()) && !member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking status.");
        }
        // change status (cancel by customer/ cancel by business / complete)
        if (booking.getStatus().equals(BookingStatus.BOOKED)) {
            booking.changeStatus(newStatus);
        }
        return BookingDetailDto.of(booking);
    }

    @Override
    public BookingDetailDto getBookingDetails(Long bookingId) {
        // get logged in member
        Member member = getLoggedInMember();

        // find the booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check the member's authority
        if (!member.equals(booking.getBusiness().getMember()) && !member.equals(booking.getMember())) {
            throw new UnauthorizedUserException("Unauthorized: You do not have permission to read the booking details.");
        }
        return BookingDetailDto.of(booking);
    }

    @Override
    public Page<Booking> getAllBookingByBusiness(Long businessId, Pageable pageable) {
        return null;
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
