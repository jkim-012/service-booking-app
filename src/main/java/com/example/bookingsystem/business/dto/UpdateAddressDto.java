package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateAddressDto {

  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request {

    private Province province;

    @NotBlank(message = "City is a required field.")
    private String city;

    @NotBlank(message = "Street address is a required field.")
    private String streetAddress;

    @NotBlank(message = "Postal code is a required field.")
    @Size(min = 6 , max = 6, message = "it must be 6 characters")
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
