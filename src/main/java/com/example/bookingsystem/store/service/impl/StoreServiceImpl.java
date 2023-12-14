package com.example.bookingsystem.store.service.impl;

import com.example.bookingsystem.exception.StoreNotFoundException;
import com.example.bookingsystem.store.dto.NewStoreDto;
import com.example.bookingsystem.store.dto.UpdateStoreDto;
import com.example.bookingsystem.store.domain.Store;
import com.example.bookingsystem.store.repository.StoreRepository;
import com.example.bookingsystem.store.service.StoreService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  @Override
  public void addStore(NewStoreDto newStoreDto) {

    // create a new store entity
    Store store = Store.builder()
        .name(newStoreDto.getName())
        .description(newStoreDto.getDescription())
        .phone(newStoreDto.getPhone())
        .zipcode(newStoreDto.getZipcode())
        .location(newStoreDto.getLocation())
        .openTime(newStoreDto.getOpenTime()) //HH:mm:ss
        .closeTime(newStoreDto.getCloseTime()) //HH:mm:ss
        .createdAt(LocalDateTime.now())
        .build();

    // save the new store
    storeRepository.save(store);

  }

  @Override
  @Transactional
  public void updateStoreInfo(Long id, UpdateStoreDto updateStoreDto) {

    // find the store by id
    Store store = storeRepository.findById(id)
        .orElseThrow(()-> new StoreNotFoundException("Store doesn't exist."));

    // update store infos
    store.updateInfo(updateStoreDto);
  }

  @Override
  @Transactional
  public void updateStoreStatus(Long id, boolean isActive) {

    // find the store by id
    Store store = storeRepository.findById(id)
        .orElseThrow(()-> new StoreNotFoundException("Store doesn't exist."));

    // update status
    store.updateStatus(isActive);

  }
}
