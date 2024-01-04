package com.example.bookingsystem.booking.dto;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.global.PageInfo;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingListDto {

    private PageInfo pageInfo;
    private List<BookingDetailDto> BookingDetailDtoList;

    public static BookingListDto of(Page<Booking> result){

        PageInfo pageInfo = PageInfo.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();

        return BookingListDto.builder()
                .pageInfo(pageInfo)
                .BookingDetailDtoList(result.map(BookingDetailDto::of).getContent())
                .build();
    }
}
