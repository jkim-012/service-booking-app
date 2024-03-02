package com.example.bookingsystem.service.service.impl;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import com.example.bookingsystem.exception.NotBusinessOwnerException;
import com.example.bookingsystem.exception.ServiceItemNotFoundException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.dto.UpdateServiceItemDto;
import com.example.bookingsystem.service.repository.ServiceItemRepository;
import com.example.bookingsystem.service.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ServiceItemServiceImpl implements ServiceItemService {

    private final MemberRepository memberRepository;
    private final BusinessRepository businessRepository;
    private final ServiceItemRepository serviceItemRepository;

    @Override
    public ServiceItemDetailDto createService(Long businessId, NewServiceItemDto newServiceDto, Member member) {
        // find the business
        Business business = getBusinessById(businessId);
        // if not business owner
        if (!business.getMember().getId().equals(member.getId())){
            throw new NotBusinessOwnerException
                    ("You are not the owner of the business. Adding a service is only allowed for business owners.");
        }
        // create service
        ServiceItem serviceItem =ServiceItem.builder()
                .name(newServiceDto.getName())
                .price((newServiceDto.getPrice()))
                .duration(newServiceDto.getDuration())
                .description(newServiceDto.getDescription())
                .business(business)
                .build();

        serviceItemRepository.save(serviceItem); //save
        return ServiceItemDetailDto.of(serviceItem);
    }

    private Business getBusinessById(Long businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(()-> new BusinessNotFoundException("Business not found with ID: " + businessId));
        return business;
    }

    @Override
    @Transactional
    public ServiceItemDetailDto updateService(Long serviceId, UpdateServiceItemDto updateServiceItemDto, Member member) {
        // find the service
        ServiceItem serviceItem = getServiceItemById(serviceId);
        // check permission
        if (!serviceItem.getBusiness().getMember().getId().equals(member.getId())){
            throw new NotBusinessOwnerException
                    ("You are not the owner of the business. Updating a service is only allowed for business owners.");
        }
        // update
        serviceItem.changeServiceInfo(updateServiceItemDto);
        return ServiceItemDetailDto.of(serviceItem);
    }

    private ServiceItem getServiceItemById(Long serviceId) {
        ServiceItem serviceItem = serviceItemRepository.findById(serviceId)
                .orElseThrow(()-> new ServiceItemNotFoundException("Service not found with ID: " + serviceId));
        return serviceItem;
    }

    @Override
    public void deleteService(Long serviceId, Member member) {
        // find the service
        ServiceItem serviceItem = getServiceItemById(serviceId);
        // check permission
        if (!serviceItem.getBusiness().getMember().getId().equals(member.getId())){
            throw new NotBusinessOwnerException
                    ("You are not the owner of the business. Deleting a service is only allowed for business owners.");
        }
        // delete
        serviceItemRepository.delete(serviceItem);
    }

    @Override
    public ServiceItemDetailDto getServiceDetails(Long serviceId) {
        // find the service
        ServiceItem serviceItem = getServiceItemById(serviceId);
        return ServiceItemDetailDto.of(serviceItem);
    }

    @Override
    public Page<ServiceItem> getAllServices(Pageable pageable) {
        Page<ServiceItem> serviceItems = serviceItemRepository.findAll(pageable);
        return serviceItems;
    }

    @Override
    public Page<ServiceItem> getAllServicesByBusiness(Long businessId, Pageable pageable) {
        Page<ServiceItem> serviceItems = serviceItemRepository.findAllByBusinessId(businessId, pageable);
        return serviceItems;
    }
}
