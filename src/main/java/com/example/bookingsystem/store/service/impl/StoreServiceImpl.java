package com.example.bookingsystem.store.service.impl;

import com.example.bookingsystem.store.dto.NewStoreDto;
import com.example.bookingsystem.store.entity.Store;
import com.example.bookingsystem.store.repository.StoreRepository;
import com.example.bookingsystem.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        .build();

    // save the new store
    storeRepository.save(store);

  }
}
