package com.example.bookingsystem.service.dto;

import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.service.domain.ServiceItem;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemDetailDto{

    private Long ServiceItemId;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private BusinessDetailDto businessDetail;

    // ServiceItem entity -> ServiceItemDetailDto
    public static ServiceItemDetailDto of(ServiceItem serviceItem) {
        return ServiceItemDetailDto.builder()
                .ServiceItemId(serviceItem.getId())
                .name(serviceItem.getName())
                .description(serviceItem.getDescription())
                .duration(serviceItem.getDuration())
                .price(serviceItem.getPrice())
                .businessDetail(BusinessDetailDto.of(serviceItem.getBusiness()))
                .build();
    }
}
