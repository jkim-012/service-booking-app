package com.example.bookingsystem.store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long storeId;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  private String phone;

  @NotBlank
  private String location;

  @NotBlank
  private String zipcode;

  // operation hours
  @NotNull
  private Time openTime;
  @NotNull
  private Time closeTime;

  // shows if it is currently open or close
  private boolean isCurrentlyOpen;

  // relationship with member

}
