package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UpdateAddressDto {

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    private Province province;
    private String city;
    private String streetAddress;
    private String postalCode;
  }

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response {
    private Long businessId;
    private Province province;
    private String city;
    private String streetAddress;
    private String postalCode;

    // Business entity -> UpdateAddressDto
    public static UpdateAddressDto.Response of(Business business) {
      return Response.builder()
          .businessId(business.getId())
          .province(business.getProvince())
          .city(business.getCity())
          .streetAddress(business.getStreetAddress())
          .postalCode(business.getPostalCode())
          .build();
    }
  }
}
