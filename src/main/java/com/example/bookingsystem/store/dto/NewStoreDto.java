package com.example.bookingsystem.store.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Time;
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

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  private String phone;

  @NotBlank
  private String location;

  @NotBlank
  @Size(min = 6 , max = 6, message = "Zipcode must be 6 characters")
  private String zipcode;

  // operation hours
  @NotNull
  private Time openTime;
  @NotNull
  private Time closeTime;

}
