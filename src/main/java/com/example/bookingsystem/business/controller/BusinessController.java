package com.example.bookingsystem.business.controller;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import com.example.bookingsystem.business.dto.BusinessListDto;
import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.business.service.BusinessService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BusinessController {

  private final BusinessService businessService;


  // API endpoint for adding a new business (only business can use this feature)
  @PostMapping("/business")
  public ResponseEntity<BusinessDetailDto> createBusiness(
      @RequestBody @Valid NewBusinessDto newBusinessDto) {

    BusinessDetailDto businessDetailDto = businessService.addBusiness(newBusinessDto);
    return ResponseEntity.ok(businessDetailDto);
  }

  // API endpoint for updating the existed business address info (only business can use this feature)
  @PutMapping("/business/{businessId}/address")
  public ResponseEntity<UpdateAddressDto.Response> updateAddress(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateAddressDto.Request request) {

    UpdateAddressDto.Response response = businessService.updateAddress(businessId, request);
    return ResponseEntity.ok(response);
  }

  // API endpoint for updating business active status (only business can use this feature)
  // active: open for bookings / inactive: not open for bookings
  @PatchMapping("/business/{businessId}/active-status")
  public ResponseEntity<?> updateBusinessActiveStatus(
      @PathVariable Long businessId,
      @RequestParam boolean isActive) {

    businessService.updateActiveStatus(businessId, isActive);
    return ResponseEntity.ok("The business active status is updated.");
  }

  // API endpoint for updating business open status (only business can use this feature)
  @PatchMapping("/business/{businessId}/open-status")
  public ResponseEntity<?> updateBusinessOpenStatus(
      @PathVariable Long businessId,
      @RequestParam boolean isCurrentlyOpen) {

    businessService.updateOpenStatus(businessId, isCurrentlyOpen);
    return ResponseEntity.ok("The business open status is updated.");
  }

  // API endpoint for updating business hours (only business can use this feature)
  @PutMapping("/business/{businessId}/hours")
  public ResponseEntity<?> updateBusinessHours(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateHoursDto updateHoursDto) {

    businessService.updateHours(businessId, updateHoursDto);
    return ResponseEntity.ok("The business hours are updated.");
  }

  // API endpoint for updating basic information (name, description, phone) (only business can use this feature)
  @PutMapping("/business/{businessId}/basic-info")
  public ResponseEntity<?> updateBasicInfo(
      @PathVariable Long businessId,
      @RequestBody @Valid UpdateBasicInfoDto updateBasicInfoDto) {

    businessService.updateBasicInfo(businessId, updateBasicInfoDto);
    return ResponseEntity.ok("The business basic information is updated.");
  }

  // API endpoint for deleting business (only business can use this feature)
  @DeleteMapping("/business/{businessId}")
  public ResponseEntity<?> deleteBusiness(
      @PathVariable Long businessId) {

    businessService.deleteBusiness(businessId);
    return ResponseEntity.ok("The business is now deleted.");
  }


  // API endpoint for reading business details
  @GetMapping("/businesses/{businessId}/")
  public ResponseEntity<BusinessDetailDto> getBusinessDetails(
      @PathVariable Long businessId) {

    BusinessDetailDto businessDetailDto = businessService.findBusiness(businessId);
    return ResponseEntity.ok(businessDetailDto);
  }

  // API endpoint for reading business list
  @GetMapping("/businesses/business-list")
  public ResponseEntity<BusinessListDto> getAllBusinesses(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size,
      @RequestParam(name = "sortBy", defaultValue = "name") String SortBy,
      @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {

    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), SortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Business> result = businessService.getAllBusinesses(pageable);

    return ResponseEntity.ok(BusinessListDto.of(result));
  }

}
