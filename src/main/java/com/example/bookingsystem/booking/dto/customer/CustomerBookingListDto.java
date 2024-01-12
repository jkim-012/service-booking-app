package com.example.bookingsystem.booking.dto.customer;

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
public class CustomerBookingListDto {

    private PageInfo pageInfo;
    private List<CustomerBookingDetailDto> list;

    public static CustomerBookingListDto of(Page<Booking> result){

        PageInfo pageInfo = PageInfo.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();

        return CustomerBookingListDto.builder()
                .pageInfo(pageInfo)
                .list(result.map(CustomerBookingDetailDto::of).getContent())
                .build();
    }
}
