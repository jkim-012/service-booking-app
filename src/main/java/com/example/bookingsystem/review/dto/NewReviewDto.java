package com.example.bookingsystem.review.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewReviewDto {

    private String title;
    private String content;
    private Double rate;

}
