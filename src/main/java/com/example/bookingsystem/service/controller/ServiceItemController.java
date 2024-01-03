package com.example.bookingsystem.service.controller;

import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ServiceItemController {

    private final ServiceItemService serviceManagementService;

    // API endpoint for adding a new service for a business
    @PostMapping("business/{businessId}/service")
    public ResponseEntity<ServiceItemDetailDto> createService(
            @PathVariable Long businessId,
            @RequestBody NewServiceItemDto newServiceItemDto) {
        ServiceItemDetailDto serviceDetailDto = serviceManagementService.createService(businessId, newServiceItemDto);
        return ResponseEntity.ok(serviceDetailDto);
    }




}
