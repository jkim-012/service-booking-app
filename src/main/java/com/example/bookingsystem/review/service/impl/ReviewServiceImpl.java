package com.example.bookingsystem.review.service.impl;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.booking.domain.BookingStatus;
import com.example.bookingsystem.booking.repository.BookingRepository;
import com.example.bookingsystem.exception.BookingNotFoundException;
import com.example.bookingsystem.exception.ReviewAlreadyExistException;
import com.example.bookingsystem.exception.ReviewNotFoundException;
import com.example.bookingsystem.exception.UnauthorizedUserException;
import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
import com.example.bookingsystem.review.repository.ReviewRepository;
import com.example.bookingsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .booking(booking)
                .build();
        // save
        reviewRepository.save(review);
        return ReviewDetailDto.of(review);
    }

    @Override
    @Transactional
    public ReviewDetailDto updateReview(Long reviewId, UpdateReviewDto updateReviewDto) {
        // find review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Booking not found with ID: " + reviewId));
        // update
        review.changeReviewDetails(updateReviewDto);
        return ReviewDetailDto.of(review);
    }
    @Override
    public ReviewDetailDto getReview(Long reviewId) {
        // find review
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Booking not found with ID: " + reviewId));
        return ReviewDetailDto.of(review);
    }

    @Override
    public Page<Review> getAllReviewsByServiceName(String keyword, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAllByBooking_ServiceItem_NameContaining(pageable, keyword);
        return reviews;
    }

    @Override
    public Page<Review> getAllReviewsByBusiness(Pageable pageable, Long businessId) {
        Page<Review> reviews = reviewRepository.findAllByBooking_Business_Id(pageable, businessId);
        return reviews;
    }

}
