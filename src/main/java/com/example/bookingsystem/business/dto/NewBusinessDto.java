package com.example.bookingsystem.business.dto;


import com.example.bookingsystem.business.domain.Province;
import java.sql.Time;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewBusinessDto {

  // Required information

  @NotBlank(message = "Business name is a required field.")
  private String name;

  @NotBlank(message = "Business description is a required field.")
  private String description;

  @NotBlank(message = "Phone number is a required field.")
  private String phone;

//  @NotBlank(message = "Province is a required field.")
  private Province province;

  @NotBlank(message = "City is a required field.")
  private String city;

  @NotBlank(message = "Street address is a required field.")
  private String streetAddress;

  @NotBlank(message = "Postal code is a required field.")
  @Size(min = 6 , max = 6, message = "it must be 6 characters")
  private String postalCode;

  // operation hours
  @NotNull(message = "Open time cannot be null")
  private Time openTime;
  @NotNull(message = "Close time cannot be null")
  private Time closeTime;

}
