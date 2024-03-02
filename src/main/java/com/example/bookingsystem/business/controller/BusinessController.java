package com.example.bookingsystem.business.controller;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.dto.*;
import com.example.bookingsystem.business.service.BusinessService;
import com.example.bookingsystem.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BusinessController {

    private final BusinessService businessService;


    //test
    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal Member member) {
        return member.getUsername();
    }

    // API endpoint for adding a new business (only business can use this feature)
    @PostMapping("/business")
    public ResponseEntity<BusinessDetailDto> createBusiness(
            @RequestBody @Valid NewBusinessDto newBusinessDto,
            @AuthenticationPrincipal Member member) {

        BusinessDetailDto businessDetailDto = businessService.addBusiness(newBusinessDto, member);
        return ResponseEntity.ok(businessDetailDto);
    }

    // API endpoint for updating the existed business address info (only business can use this feature)
    @PutMapping("/business/{businessId}/address")
    public ResponseEntity<UpdateAddressDto.Response> updateAddress(
            @PathVariable Long businessId,
            @RequestBody @Valid UpdateAddressDto.Request request,
            @AuthenticationPrincipal Member member) {

        UpdateAddressDto.Response response = businessService.updateAddress(businessId, request, member);
        return ResponseEntity.ok(response);
    }

    // API endpoint for updating business active status (only business can use this feature)
    // active: open for bookings / inactive: not open for bookings
    @PatchMapping("/business/{businessId}/active-status")
    public ResponseEntity<?> updateBusinessActiveStatus(
            @PathVariable Long businessId,
            @RequestParam boolean isActive,
            @AuthenticationPrincipal Member member) {

        businessService.updateActiveStatus(businessId, isActive, member);
        return ResponseEntity.ok("The business active status is updated.");
    }

    // API endpoint for updating business open status (only business can use this feature)
    @PatchMapping("/business/{businessId}/open-status")
    public ResponseEntity<?> updateBusinessOpenStatus(
            @PathVariable Long businessId,
            @RequestParam boolean isCurrentlyOpen,
            @AuthenticationPrincipal Member member) {

        businessService.updateOpenStatus(businessId, isCurrentlyOpen, member);
        return ResponseEntity.ok("The business open status is updated.");
    }

    // API endpoint for updating business hours (only business can use this feature)
    @PutMapping("/business/{businessId}/hours")
    public ResponseEntity<?> updateBusinessHours(
            @PathVariable Long businessId,
            @RequestBody @Valid UpdateHoursDto updateHoursDto,
            @AuthenticationPrincipal Member member) {

        businessService.updateHours(businessId, updateHoursDto, member);
        return ResponseEntity.ok("The business hours are updated.");
    }

    // API endpoint for updating basic information (name, description, phone) (only business can use this feature)
    @PutMapping("/business/{businessId}/basic-info")
    public ResponseEntity<?> updateBasicInfo(
            @PathVariable Long businessId,
            @RequestBody @Valid UpdateBasicInfoDto updateBasicInfoDto,
            @AuthenticationPrincipal Member member) {

        businessService.updateBasicInfo(businessId, updateBasicInfoDto, member);
        return ResponseEntity.ok("The business basic information is updated.");
    }

    // API endpoint for deleting business (only business can use this feature)
    @DeleteMapping("/business/{businessId}")
    public ResponseEntity<?> deleteBusiness(
            @PathVariable Long businessId,
            @AuthenticationPrincipal Member member) {

        businessService.deleteBusiness(businessId, member);
        return ResponseEntity.ok("The business is now deleted.");
    }


    // API endpoint for reading business details
    @GetMapping("/businesses/{businessId}")
    public ResponseEntity<BusinessDetailDto> getBusinessDetails(
            @PathVariable Long businessId) {

        BusinessDetailDto businessDetailDto = businessService.findBusiness(businessId);
        return ResponseEntity.ok(businessDetailDto);
    }

    // API endpoint for reading business list
    @GetMapping("/businesses/list")
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
