package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.domain.Province;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.bookingsystem.member.dto.MemberDetailDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetailDto {

  private Long id;
  private String name;
  private String description;
  private String phone;

  // address
  private Province province;
  private String city;
  private String streetAddress;
  private String postalCode;

  // operation hours
  private LocalTime openTime;
  private LocalTime closeTime;

  // current open status
  private Boolean isCurrentlyOpen;
  // current business status
  private Boolean isActive;

  // store create & update - date and time
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // Business entity -> BusinessDetailDto
  public static BusinessDetailDto of(Business business) {
    return BusinessDetailDto.builder()
        .id(business.getId())
        .name(business.getName())
        .description(business.getDescription())
        .phone(business.getPhone())
        .province(business.getProvince())
        .city(business.getCity())
        .streetAddress(business.getStreetAddress())
        .postalCode(business.getPostalCode())
        .openTime(business.getOpenTime())
        .closeTime(business.getCloseTime())
        .isCurrentlyOpen(business.getIsCurrentlyOpen())
        .isActive(business.getIsActive())
        .createdAt(business.getCreatedAt())
        .updatedAt(business.getUpdatedAt())
        .build();
  }
}
