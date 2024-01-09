package com.example.bookingsystem.review.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.exception.BookingNotFoundException;
import com.example.bookingsystem.exception.ReviewAlreadyExistException;
import com.example.bookingsystem.exception.UnauthorizedUserException;
import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.repository.ReviewRepository;
import com.example.bookingsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ReviewDetailDto createReview(Long bookingId, NewReviewDto newReviewDto) {
        // check if there is already a review for the booking id
        Optional<Review> byBookingId = reviewRepository.findByBookingId(bookingId);
        if (byBookingId.isPresent()) {
            throw new ReviewAlreadyExistException("A review already exists for the booking.");
        }
        // find booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        // check booking status
        if (!booking.getStatus().equals(BookingStatus.COMPLETED)){
            throw new UnauthorizedUserException("Check the booking status: " + booking.getStatus());
        }
        // review
        Review review = Review.builder()
                .title(newReviewDto.getTitle())
                .content(newReviewDto.getContent())
                .rate(newReviewDto.getRate())
                .serviceItem(booking.getServiceItem())
                .booking(booking)
                .build();
        // save
        reviewRepository.save(review);
        return ReviewDetailDto.of(review);
    }
}
