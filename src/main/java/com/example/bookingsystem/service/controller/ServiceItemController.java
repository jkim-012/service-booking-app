package com.example.bookingsystem.service.controller;

import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.dto.ServiceItemListDto;
import com.example.bookingsystem.service.dto.UpdateServiceItemDto;
import com.example.bookingsystem.service.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class ServiceItemController {

    private final ServiceItemService serviceItemService;

    // API endpoint for adding a new service for a business (only business can use this feature)
    @PostMapping("/business/{businessId}/service")
    public ResponseEntity<ServiceItemDetailDto> createService(
            @PathVariable Long businessId,
            @RequestBody NewServiceItemDto newServiceItemDto) {

        ServiceItemDetailDto serviceItemDetailDto = serviceItemService.createService(businessId, newServiceItemDto);
        return ResponseEntity.ok(serviceItemDetailDto);
    }


    // API endpoint for updating service basic information (only business can use this feature)
    @PutMapping("/business/service/{serviceId}")
    public ResponseEntity<ServiceItemDetailDto> updateService(
            @PathVariable Long serviceId,
            @RequestBody UpdateServiceItemDto updateServiceItemDto) {

        ServiceItemDetailDto serviceItemDetailDto = serviceItemService.updateService(serviceId, updateServiceItemDto);
        return ResponseEntity.ok(serviceItemDetailDto);
    }

    // API endpoint for deleting service (only business can use this feature)
    @DeleteMapping("/business/service/{serviceId}")
    public ResponseEntity<?> deleteService(
            @PathVariable Long serviceId) {

        serviceItemService.deleteService(serviceId);
        return ResponseEntity.ok("The service is now deleted.");
    }

    // API endpoint for reading a service details
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ServiceItemDetailDto> getService(
            @PathVariable Long serviceId) {

        ServiceItemDetailDto serviceItemDetailDto = serviceItemService.getServiceDetails(serviceId);
        return ResponseEntity.ok(serviceItemDetailDto);
    }

    // API endpoint for reading all registered services
    @GetMapping("/service/list")
    public ResponseEntity<ServiceItemListDto> getAllServices(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ServiceItem> result = serviceItemService.getAllServices(pageable);
        return ResponseEntity.ok(ServiceItemListDto.of(result));
    }


    // API endpoint for reading all services by business
    @GetMapping("/services/business/{businessId}")
    public ResponseEntity<ServiceItemListDto> getAllServicesByBusiness(
            @PathVariable Long businessId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder),sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ServiceItem> result = serviceItemService.getAllServicesByBusiness(businessId, pageable);
        return ResponseEntity.ok(ServiceItemListDto.of(result));
    }


}
