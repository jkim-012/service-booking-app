package com.example.bookingsystem.business.service;

import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;

;

public interface BusinessService {
  BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto);
  UpdateAddressDto.Response updateAddress(Long businessId, UpdateAddressDto.Request request);
  void updateActiveStatus(Long businessId, boolean isActive);
  void updateOpenStatus(Long businessId, boolean isCurrentlyOpen);
  void updateHours(Long businessId, UpdateHoursDto updateHoursDto);

}
