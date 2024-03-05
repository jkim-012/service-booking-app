package com.example.bookingsystem.review.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewDto {

    @Size(min = 5, max = 20, message = "Title should be between 5 and 20 characters.")
    private String title;

    @Size(min = 5, max = 300, message = "Content should be between 5 and 300 characters.")
    private String content;

    @DecimalMin(value = "0.0", message = "Rate should be between 0 and 5.0.")
    @DecimalMax(value = "5.0", message = "Rate should be between 0 and 5.0.")
    private Double rate;

}
