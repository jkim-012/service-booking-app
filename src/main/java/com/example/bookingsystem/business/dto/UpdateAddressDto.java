package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateAddressDto {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Request {

    private Province province;
    private String city;
    private String streetAddress;
    private String postalCode;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {
    private Long id;
    private Province province;
    private String city;
    private String streetAddress;
    private String postalCode;

    // Business entity -> UpdateAddressDto
    public static UpdateAddressDto.Response of(Business business) {
      return Response.builder()
          .id(business.getId())
          .province(business.getProvince())
          .city(business.getCity())
          .streetAddress(business.getStreetAddress())
          .postalCode(business.getPostalCode())
          .build();
    }
  }
}
