package com.example.bookingsystem.review.service;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    ReviewDetailDto createReview(Long bookingId, NewReviewDto newReviewDto, Member member);
    ReviewDetailDto updateReview(Long reviewId, UpdateReviewDto updateReviewDto, Member member);
    Page<Review> getAllReviewsByBusiness(Pageable pageable, Long businessId);
    ReviewDetailDto getReview(Long reviewId);
    Page<Review> getAllReviewsByServiceName(String keyword, Pageable pageable);
}
