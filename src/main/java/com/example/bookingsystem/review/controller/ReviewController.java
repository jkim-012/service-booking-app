package com.example.bookingsystem.review.controller;

import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
import com.example.bookingsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;


    // API endpoint for creating a review for a complete booking
    @PostMapping("/booking/{bookingId}/review")
    public ResponseEntity<ReviewDetailDto> createReview(
            @PathVariable Long bookingId,
            @RequestBody NewReviewDto newReviewDto) {

        ReviewDetailDto reviewDetailDto = reviewService.createReview(bookingId, newReviewDto);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for updating a review for a complete booking
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDetailDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody UpdateReviewDto updateReviewDto){

        ReviewDetailDto reviewDetailDto = reviewService.updateReview(reviewId, updateReviewDto);
        return ResponseEntity.ok(reviewDetailDto);
    }


}
