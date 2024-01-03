package com.example.bookingsystem.business.repository;

import com.example.bookingsystem.business.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByMemberId(Long id);
}
