package com.example.bookingsystem.booking.repository;

import com.example.bookingsystem.booking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByScheduledAt(LocalDateTime scheduledAt);
}
