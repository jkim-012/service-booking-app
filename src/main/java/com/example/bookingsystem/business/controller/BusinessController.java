package com.example.bookingsystem.business.controller;

import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.business.service.BusinessService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/business")
@RequiredArgsConstructor
@RestController
public class BusinessController {

  private final BusinessService businessService;


  // API endpoint for adding a new business
  @PostMapping()
  public ResponseEntity<BusinessDetailDto> createBusiness(
      @RequestBody @Valid NewBusinessDto newBusinessDto) {

    BusinessDetailDto businessDetailDto = businessService.addBusiness(newBusinessDto);
    return ResponseEntity.ok(businessDetailDto);
  }


  // API endpoint for updating the existed business address info
  @PutMapping("/{businessId}/address")
  public ResponseEntity<UpdateAddressDto.Response> updateAddress(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateAddressDto.Request request){
    UpdateAddressDto.Response response = businessService.updateAddress(businessId, request);
    return ResponseEntity.ok(response);
  }

  // API endpoint for updating business active status
  @PatchMapping("/{businessId}/active-status")
  public ResponseEntity<?> updateBusinessActiveStatus(
      @PathVariable Long businessId,
      @RequestParam boolean isActive) {
    businessService.updateActiveStatus(businessId, isActive);
    return ResponseEntity.ok("Business active status is updated.");
  }

  // API endpoint for updating business open status
  @PatchMapping("/{businessId}/open-status")
  public ResponseEntity<?> updateBusinessOpenStatus(
      @PathVariable Long businessId,
      @RequestParam boolean isCurrentlyOpen) {

    businessService.updateOpenStatus(businessId, isCurrentlyOpen);
    return ResponseEntity.ok("Business open status is updated.");
  }

  // API endpoint for updating business hours
  @PutMapping("/{businessId}/hours")
  public ResponseEntity<?> updateBusinessHours(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateHoursDto updateHoursDto){
    businessService.updateHours(businessId, updateHoursDto);
    return ResponseEntity.ok("Business hours are updated.");
  }

  // API endpoint for updating basic information (name, description, phone)
  @PutMapping("/{businessId}/basic-info")
  public ResponseEntity<?> updateBasicInfo(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateBasicInfoDto updateBasicInfoDto) {
    businessService.updateBasicInfo(businessId, updateBasicInfoDto);
    return ResponseEntity.ok("Business basic information is updated.");
  }

  // API endpoint for reading business details
  @GetMapping("/{businessId}/details")
  public ResponseEntity<BusinessDetailDto> getBusinessDetails(
      @PathVariable Long businessId){
    BusinessDetailDto businessDetailDto = businessService.findBusiness(businessId);
    return ResponseEntity.ok(businessDetailDto);
  }

}
