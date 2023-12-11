package com.example.bookingsystem.store.service;

import com.example.bookingsystem.store.dto.NewStoreDto;
import com.example.bookingsystem.store.dto.UpdateStoreDto;

public interface StoreService {
  void addStore(NewStoreDto newStoreDto);
  void updateStoreInfo(Long id, UpdateStoreDto updateStoreDto);
  void updateStoreStatus(Long id, boolean isActive);
}
