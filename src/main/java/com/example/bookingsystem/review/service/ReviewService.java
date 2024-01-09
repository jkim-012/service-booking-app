package com.example.bookingsystem.review.service;

import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;

public interface ReviewService {
    ReviewDetailDto createReview(Long bookingId, NewReviewDto newReviewDto);
    ReviewDetailDto updateReview(Long reviewId, UpdateReviewDto updateReviewDto);

}
