package com.example.bookingsystem.review.controller;

import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.review.dto.NewReviewDto;
import com.example.bookingsystem.review.dto.ReviewDetailDto;
import com.example.bookingsystem.review.dto.ReviewListDto;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
import com.example.bookingsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;


    // API endpoint for creating a review for a complete booking
    @PostMapping("/bookings/{bookingId}/reviews")
    public ResponseEntity<ReviewDetailDto> createReview(
            @PathVariable Long bookingId,
            @RequestBody NewReviewDto newReviewDto) {

        ReviewDetailDto reviewDetailDto = reviewService.createReview(bookingId, newReviewDto);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for updating a review for a complete booking
    @PutMapping("/bookings/reviews/{reviewId}")
    public ResponseEntity<ReviewDetailDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody UpdateReviewDto updateReviewDto) {

        ReviewDetailDto reviewDetailDto = reviewService.updateReview(reviewId, updateReviewDto);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for reading a review
    @GetMapping("/bookings/reviews/{reviewId}")
    public ResponseEntity<ReviewDetailDto> readReviewDetails(
            @PathVariable Long reviewId){

        ReviewDetailDto reviewDetailDto = reviewService.getReview(reviewId);
        return ResponseEntity.ok(reviewDetailDto);
    }


    // API endpoint for getting all reviews by business
    @GetMapping("/business/bookings/reviews/{businessId}")
    public ResponseEntity<ReviewListDto> getAllReviewsByBusiness(
            @PathVariable Long businessId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder){

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> result = reviewService.getAllReviewsByBusiness(pageable, businessId);
        return ResponseEntity.ok(ReviewListDto.of(result));
    }


}
