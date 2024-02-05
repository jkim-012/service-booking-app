package com.example.bookingsystem.review.service;

import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewDetailDto createReview(Long bookingId, NewReviewDto newReviewDto);
    ReviewDetailDto updateReview(Long reviewId, UpdateReviewDto updateReviewDto);
    Page<Review> getAllReviewsByBusiness(Pageable pageable, Long businessId);
    ReviewDetailDto getReview(Long reviewId);
}
