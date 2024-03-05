package com.example.bookingsystem.review.controller;

import com.example.bookingsystem.member.domain.Member;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;


    // API endpoint for creating a review for a complete booking (only customer can write a review)
    @PostMapping("/customer/bookings/{bookingId}/reviews")
    public ResponseEntity<ReviewDetailDto> createReview(
            @PathVariable Long bookingId,
            @RequestBody @Valid NewReviewDto newReviewDto,
            @AuthenticationPrincipal Member member) {

        ReviewDetailDto reviewDetailDto = reviewService.createReview(bookingId, newReviewDto, member);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for updating a review for a complete booking  (only customer can update their review)
    @PutMapping("/customer/reviews/{reviewId}")
    public ResponseEntity<ReviewDetailDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody @Valid UpdateReviewDto updateReviewDto,
            @AuthenticationPrincipal Member member) {

        ReviewDetailDto reviewDetailDto = reviewService.updateReview(reviewId, updateReviewDto, member);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for reading a review
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewDetailDto> readReviewDetails(
            @PathVariable Long reviewId) {

        ReviewDetailDto reviewDetailDto = reviewService.getReview(reviewId);
        return ResponseEntity.ok(reviewDetailDto);
    }

    // API endpoint for getting all reviews by business
    @GetMapping("/businesses/{businessId}/reviews")
    public ResponseEntity<ReviewListDto> getAllReviewsByBusiness(
            @PathVariable Long businessId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Review> result = reviewService.getAllReviewsByBusiness(pageable, businessId);
        return ResponseEntity.ok(ReviewListDto.of(result));
    }

    // API endpoint for searching all reviews by service name (keyword input)
    @GetMapping("/reviews/services")
    public ResponseEntity<ReviewListDto> getAllReviewsByServiceName(
            @RequestParam(name = "keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Review> result = reviewService.getAllReviewsByServiceName(keyword, pageable);
        return ResponseEntity.ok(ReviewListDto.of(result));
    }


}
