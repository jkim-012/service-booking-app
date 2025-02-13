package com.example.bookingsystem.business.service;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

;

public interface BusinessService {
  BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto, Member member);
  UpdateAddressDto.Response updateAddress(Long businessId, UpdateAddressDto.Request request, Member member);
  void updateActiveStatus(Long businessId, boolean isActive, Member member);
  void updateOpenStatus(Long businessId, boolean isCurrentlyOpen, Member member);
  void updateHours(Long businessId, UpdateHoursDto updateHoursDto, Member member);
  void updateBasicInfo(Long businessId, UpdateBasicInfoDto updateBasicInfoDto, Member member);
  BusinessDetailDto findBusiness(Long businessId);
  Page<Business> getAllBusinesses(Pageable pageable);
  void deleteBusiness(Long businessId, Member member);

}
