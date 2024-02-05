package com.example.bookingsystem.review.dto;

import com.example.bookingsystem.global.PageInfo;
import com.example.bookingsystem.review.domain.Review;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDto {

    private PageInfo pageInfo;
    private List<ReviewDetailDto> list;

    public static ReviewListDto of(Page<Review> result) {

        PageInfo pageInfo = PageInfo.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();

        return ReviewListDto.builder()
                .pageInfo(pageInfo)
                .list(result.map(ReviewDetailDto::of).getContent())
                .build();
    }
}
