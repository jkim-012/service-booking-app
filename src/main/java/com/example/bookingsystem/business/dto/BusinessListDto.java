package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import java.util.List;

import com.example.bookingsystem.global.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessListDto {

  private PageInfo pageInfo;
  private List<BusinessDetailDto> businessDetailDtoList;

  // page 정보와 주문상품 리스트를 반환
  public static BusinessListDto of(Page<Business> result) {

    PageInfo pageInfo = PageInfo.builder()
        .page(result.getNumber())
        .size(result.getSize())
        .totalPage(result.getTotalPages())
        .totalElements(result.getTotalElements())
        .build();

    return BusinessListDto.builder()
        .pageInfo(pageInfo)
        .businessDetailDtoList(result.map(BusinessDetailDto::of).getContent())
        .build();
  }

}
