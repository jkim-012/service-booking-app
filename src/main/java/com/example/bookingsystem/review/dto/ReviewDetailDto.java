package com.example.bookingsystem.review.dto;

import com.example.bookingsystem.booking.dto.BookingDetailDto;
import com.example.bookingsystem.review.domain.Review;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailDto {

    private Long reviewId;
    private String title;
    private String content;
    private Double rate;
    private ServiceItemDetailDto serviceItem;
    private BookingDetailDto booking;
    public static ReviewDetailDto of(Review review) {
        return ReviewDetailDto.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .rate(review.getRate())
                .serviceItem(ServiceItemDetailDto.of(review.getServiceItem()))
                .booking(BookingDetailDto.of(review.getBooking()))
                .build();
    }
}
