package com.example.bookingsystem.service.service;

import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;

public interface ServiceItemService {
  ServiceItemDetailDto createService(Long businessId, NewServiceItemDto newServiceDto);

}
