package com.example.bookingsystem.business.controller;

import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.service.BusinessService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<BusinessDetailDto> addBusiness(
      @RequestBody @Valid NewBusinessDto newBusinessDto) {

    BusinessDetailDto businessDetailDto = businessService.addBusiness(newBusinessDto);
    return ResponseEntity.ok(businessDetailDto);
  }


  // API endpoint for updating the existed business address info
  @PutMapping("/{id}/address")
  public ResponseEntity<UpdateAddressDto.Response> updateAddress(
      @PathVariable Long id,
      @RequestBody @Valid UpdateAddressDto.Request request){
    UpdateAddressDto.Response response = businessService.updateAddress(id, request);
    return ResponseEntity.ok(response);
  }

  // API endpoint for updating business active status
  @PatchMapping("/{id}/active-status")
  public ResponseEntity<?> activateBusiness(@PathVariable Long id,
      @RequestParam boolean isActive) {

    businessService.updateBusinessStatus(id, isActive);
    return ResponseEntity.ok("Business is now active.");
  }

  // API endpoint for updating business open status
  @PatchMapping("/{id}/oepn-status")
  public ResponseEntity<?> openBusiness(@PathVariable Long id,
      @RequestParam boolean isCurrentlyOpen) {

    businessService.openBusinessStatus(id, isCurrentlyOpen);
    return ResponseEntity.ok("Business is now open.");
  }


}
