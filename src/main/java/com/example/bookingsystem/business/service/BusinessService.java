package com.example.bookingsystem.business.service;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

;

public interface BusinessService {
  BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto);
  UpdateAddressDto.Response updateAddress(Long businessId, UpdateAddressDto.Request request);
  void updateActiveStatus(Long businessId, boolean isActive);
  void updateOpenStatus(Long businessId, boolean isCurrentlyOpen);
  void updateHours(Long businessId, UpdateHoursDto updateHoursDto);
  void updateBasicInfo(Long businessId, UpdateBasicInfoDto updateBasicInfoDto);
  BusinessDetailDto findBusiness(Long businessId);
  Page<Business> getAllBusinesses(Pageable pageable);
  void deleteBusiness(Long businessId);

}
