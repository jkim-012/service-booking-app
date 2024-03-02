package com.example.bookingsystem.service.service;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.dto.UpdateServiceItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceItemService {
    ServiceItemDetailDto createService(Long businessId, NewServiceItemDto newServiceDto, Member member);

    ServiceItemDetailDto updateService(Long serviceId, UpdateServiceItemDto updateServiceItemDto, Member member);

    void deleteService(Long serviceId, Member member);

    ServiceItemDetailDto getServiceDetails(Long serviceId);

    Page<ServiceItem> getAllServices(Pageable pageable);

    Page<ServiceItem> getAllServicesByBusiness(Long businessId, Pageable pageable);
}
