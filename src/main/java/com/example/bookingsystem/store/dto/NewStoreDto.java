package com.example.bookingsystem.store.dto;


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
public class NewStoreDto {

  // Required information when adding a new store

  @NotBlank(message = "Store name is a required field.")
  private String name;

  @NotBlank(message = "Store description is a required field.")
  private String description;

  @NotBlank(message = "Phone number is a required field.")
  private String phone;

  @NotBlank(message = "Store location is a required field.")
  private String location;

  @NotBlank(message = "Store zipcode is a required field.")
  @Size(min = 6 , max = 6, message = "Zipcode must be 6 characters")
  private String zipcode;

  // operation hours
  @NotNull(message = "Open time cannot be null")
  private Time openTime;
  @NotNull(message = "Close time cannot be null")
  private Time closeTime;

}
