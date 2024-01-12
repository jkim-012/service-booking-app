package com.example.bookingsystem.booking.dto.business;

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
public class BusinessBookingListDto {

    private PageInfo pageInfo;
    private List<BusinessBookingDetailDto> list;

    public static BusinessBookingListDto of(Page<Booking> result){

        PageInfo pageInfo = PageInfo.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();

        return BusinessBookingListDto.builder()
                .pageInfo(pageInfo)
                .list(result.map(BusinessBookingDetailDto::of).getContent())
                .build();
    }
}
