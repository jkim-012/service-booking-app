package com.example.bookingsystem.store.controller;

import com.example.bookingsystem.store.dto.NewStoreDto;
import com.example.bookingsystem.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/store")
@RequiredArgsConstructor
@RestController
public class StoreController {

  private final StoreService storeService;


  // API endpoint for adding a new store
  @PostMapping()
  public ResponseEntity<?> addStore(@RequestBody @Valid NewStoreDto newStoreDto){

    storeService.addStore(newStoreDto);
    return ResponseEntity.ok("New store added successfully");
  }

}
