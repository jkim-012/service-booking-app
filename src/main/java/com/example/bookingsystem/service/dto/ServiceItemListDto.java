package com.example.bookingsystem.service.dto;

import com.example.bookingsystem.global.PageInfo;
import com.example.bookingsystem.service.domain.ServiceItem;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemListDto {

    private PageInfo pageInfo;
    private List<ServiceItemDetailDto> serviceItemDetailDtos;
    public static ServiceItemListDto of(Page<ServiceItem> result) {

        PageInfo pageInfo = PageInfo.builder()
                .page(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPage(result.getTotalPages())
                .build();

        return ServiceItemListDto.builder()
                .pageInfo(pageInfo)
                .serviceItemDetailDtos(result.map(ServiceItemDetailDto::of).getContent())
                .build();

    }
}
