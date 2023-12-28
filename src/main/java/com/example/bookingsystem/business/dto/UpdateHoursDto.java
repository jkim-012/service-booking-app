package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import java.time.LocalTime;
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
public class UpdateHoursDto {

  private LocalTime openTime;
  private LocalTime closeTime;
}

