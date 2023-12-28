package com.example.bookingsystem.business.service.impl;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.business.service.BusinessService;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BusinessServiceImpl implements BusinessService {

  private final BusinessRepository businessRepository;

  @Override
  public BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto) {
    // create a new business entity
    Business business = Business.create(newBusinessDto);
    // save
    businessRepository.save(business);
    return BusinessDetailDto.of(business);
  }

  @Override
  @Transactional
  public UpdateAddressDto.Response updateAddress(Long businessId, UpdateAddressDto.Request request) {
    // find the business
    Business business = businessRepository.findById(businessId)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update business address
    business.updateAddress(request);
    return UpdateAddressDto.Response.of(business);
  }

  @Override
  @Transactional
  public void updateActiveStatus(Long businessId, boolean isActive) {
    // find the business
    Business business = businessRepository.findById(businessId)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update status
    business.updateActiveStatus(isActive);
  }

  @Override
  @Transactional
  public void updateOpenStatus(Long businessId, boolean isCurrentlyOpen) {
    // find the business
    Business business = businessRepository.findById(businessId)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update status
    business.updateOpenStatus(isCurrentlyOpen);
  }

  @Override
  public void updateHours(Long businessId, UpdateHoursDto updateHoursDto) {
    // find the business
    Business business = businessRepository.findById(businessId)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update status
    business.updateHours(updateHoursDto);
  }
}
