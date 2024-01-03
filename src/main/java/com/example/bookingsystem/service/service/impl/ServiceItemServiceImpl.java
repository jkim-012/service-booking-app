package com.example.bookingsystem.service.service.impl;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import com.example.bookingsystem.exception.NotBusinessOwnerException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.service.domain.ServiceItem;
import com.example.bookingsystem.service.dto.NewServiceItemDto;
import com.example.bookingsystem.service.dto.ServiceItemDetailDto;
import com.example.bookingsystem.service.repository.ServiceItemRepository;
import com.example.bookingsystem.service.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceItemServiceImpl implements ServiceItemService {

    private final MemberRepository memberRepository;
    private final BusinessRepository businessRepository;
    private final ServiceItemRepository serviceItemRepository;

    @Override
    public ServiceItemDetailDto createService(Long businessId, NewServiceItemDto newServiceDto) {
        // get logged in member info
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // find the business
        Business business = businessRepository.findById(businessId)
                .orElseThrow(()-> new BusinessNotFoundException("Business not found with ID: " + businessId));

        // if not business owner
        if (!business.getMember().equals(member)){
            throw new NotBusinessOwnerException
                    ("You are not the owner of the business. Adding a service is only allowed for business owners.");
        }

        // create service
        ServiceItem serviceItem = ServiceItem.builder()
                .price((newServiceDto.getPrice()))
                .duration(newServiceDto.getDuration())
                .description(newServiceDto.getDescription())
                .business(business)
                .build();

        serviceItemRepository.save(serviceItem); //save
        return ServiceItemDetailDto.of(serviceItem);
    }
}
