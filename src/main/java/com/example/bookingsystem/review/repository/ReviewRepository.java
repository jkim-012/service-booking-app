package com.example.bookingsystem.review.repository;

import com.example.bookingsystem.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByBookingId(Long bookingId);
    Page<Review> findAllByBooking_Business_Id(Pageable pageable, Long businessId);
    Page<Review> findAllByBooking_ServiceItem_NameContaining(Pageable pageable, String keyword);
}
