package com.example.bookingsystem.business.service.impl;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto.Request;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.business.service.BusinessService;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BusinessServiceImpl implements BusinessService {

  private final BusinessRepository businessRepository;
  private final MemberRepository memberRepository;

  @Override
  public BusinessDetailDto addBusiness(NewBusinessDto newBusinessDto) {
    // get a logged in member
    Member member = getLoggedInMember();
    // create a new business entity
    Business business = Business.create(newBusinessDto, member);
    // save
    businessRepository.save(business);
    return BusinessDetailDto.of(business);
  }

  @Override
  @Transactional
  public UpdateAddressDto.Response updateAddress(Long businessId, Request request) {
    // find the business
    Business business = getBusiness(businessId);
    // update business address
    business.changeAddress(request);
    return UpdateAddressDto.Response.of(business);
  }


  @Override
  @Transactional
  public void updateActiveStatus(Long businessId, boolean isActive) {
    // find the business
    Business business = getBusiness(businessId);
    // update status
    business.changeActiveStatus(isActive);
  }

  @Override
  @Transactional
  public void updateOpenStatus(Long businessId, boolean isCurrentlyOpen) {
    // find the business
    Business business = getBusiness(businessId);
    // update status
    business.changeOpenStatus(isCurrentlyOpen);
  }

  @Override
  @Transactional
  public void updateHours(Long businessId, UpdateHoursDto updateHoursDto) {
    // find the business
    Business business = getBusiness(businessId);
    // update hours
    business.changeHours(updateHoursDto);
  }

  @Override
  @Transactional
  public void updateBasicInfo(Long businessId, UpdateBasicInfoDto updateBasicInfoDto) {
    // find the business
    Business business = getBusiness(businessId);
    // update basic information
    business.changeBasicInfo(updateBasicInfoDto);
  }

  @Override
  public BusinessDetailDto findBusiness(Long businessId) {
    return BusinessDetailDto.of(getBusiness(businessId));
  }

  @Override
  public Page<Business> getAllBusinesses(Pageable pageable) {
    Page<Business> businesses = businessRepository.findAll(pageable);
    return businesses;
  }

  @Override
  public void deleteBusiness(Long businessId) {
    // find the business
    Business business = getBusiness(businessId);
    // delete the business
    businessRepository.delete(business);
  }

  private Business getBusiness(Long businessId) {
    Business business = businessRepository.findById(businessId)
        .orElseThrow(()-> new BusinessNotFoundException("Business not found with ID: " + businessId));
    return business;
  }

  private Member getLoggedInMember() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    return member;
  }
}
