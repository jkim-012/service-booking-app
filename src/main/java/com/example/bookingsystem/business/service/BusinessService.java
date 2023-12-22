package com.example.bookingsystem.business.service;

import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;

public interface BusinessService {
  BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto);
  UpdateAddressDto.Response updateAddress(Long id, UpdateAddressDto.Request request);
  void updateBusinessStatus(Long id, boolean isActive);
  void openBusinessStatus(Long id, boolean isCurrentlyOpen);
}
