package com.example.bookingsystem.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBasicInfoDto {

  private String name;

  @Size(min = 10, max = 200, message = "Business description should be between 10 and 200 characters.")
  private String description;

  private String phone;
}
