package com.example.bookingsystem.service.repository;

import com.example.bookingsystem.service.domain.ServiceItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
    Page<ServiceItem> findAllByBusinessId(Long businessId, Pageable pageable);
}
