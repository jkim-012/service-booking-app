package com.example.bookingsystem.business.service.impl;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
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
    Business business = Business.createBusiness(newBusinessDto);
    // save
    businessRepository.save(business);
    return BusinessDetailDto.of(business);
  }

  @Override
  @Transactional
  public UpdateAddressDto.Response updateAddress(Long id, UpdateAddressDto.Request request) {
    // find the business
    Business business = businessRepository.findById(id)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));

    // update business address
    business.updateInfo(request);
    return UpdateAddressDto.Response.of(business);
  }

  @Override
  @Transactional
  public void updateBusinessStatus(Long id, boolean isActive) {
    // find the store by id
    Business business = businessRepository.findById(id)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update status
    business.updateActiveStatus(isActive);
  }

  @Override
  @Transactional
  public void openBusinessStatus(Long id, boolean isCurrentlyOpen) {
    // find the store by id
    Business business = businessRepository.findById(id)
        .orElseThrow(()-> new BusinessNotFoundException("Business doesn't exist."));
    // update status
    business.updateOpenStatus(isCurrentlyOpen);
  }
}
