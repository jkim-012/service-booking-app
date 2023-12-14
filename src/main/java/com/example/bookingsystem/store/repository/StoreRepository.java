package com.example.bookingsystem.store.repository;

import com.example.bookingsystem.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {


}
