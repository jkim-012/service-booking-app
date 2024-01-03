package com.example.bookingsystem.service.service;

import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.dto.UpdateServiceItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceItemService {
  ServiceItemDetailDto createService(Long businessId, NewServiceItemDto newServiceDto);
  ServiceItemDetailDto updateService(Long serviceId, UpdateServiceItemDto updateServiceItemDto);
  void deleteService(Long serviceId);
  ServiceItemDetailDto getServiceDetails(Long serviceId);
  Page<ServiceItem> getAllServices(Pageable pageable);
}
