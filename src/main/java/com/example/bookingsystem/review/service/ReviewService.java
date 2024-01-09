package com.example.bookingsystem.review.service;

import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;

public interface ReviewService {
    ReviewDetailDto createReview(Long bookingId, NewReviewDto newReviewDto);
}
