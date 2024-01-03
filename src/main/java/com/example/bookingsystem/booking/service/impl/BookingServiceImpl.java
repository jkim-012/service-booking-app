package com.example.bookingsystem.booking.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.NewBookingDto;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.booking.service.BookingService;
import com.example.bookingsystem.exception.BookingNotAvailableException;
import com.example.bookingsystem.exception.NotCustomerException;
import com.example.bookingsystem.exception.ServiceItemNotFoundException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.domain.Role;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.repository.ServiceItemRepository;
import lombok.RequiredArgsConstructor;
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
        if (byScheduledAt.isPresent()){
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
                .build();

        return BookingDetailDto.of(booking);
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
                .orElseThrow(()-> new ServiceItemNotFoundException("Service not found with ID: " + serviceId));
        return serviceItem;
    }

}
