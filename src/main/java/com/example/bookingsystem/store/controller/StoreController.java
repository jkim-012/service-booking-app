package com.example.bookingsystem.store.controller;

import com.example.bookingsystem.store.dto.NewStoreDto;
import com.example.bookingsystem.store.dto.UpdateStoreDto;
import com.example.bookingsystem.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


  // API endpoint for updating the existed store information
  @PutMapping("/{id}/info")
  public ResponseEntity<?> updateStoreInfo(@PathVariable Long id, @RequestBody @Valid UpdateStoreDto updateStoreDto){

    storeService.updateStoreInfo(id, updateStoreDto);
    return ResponseEntity.ok("Store information updated successfully");
  }

  // API endpoint for updating store active status
  @PatchMapping("/{id}/status")
  public ResponseEntity<?> updateStoreStatus(@PathVariable Long id, @RequestParam boolean isActive){

    storeService.updateStoreStatus(id, isActive);
    return ResponseEntity.ok("Store status is updated successfully.");
  }





}
