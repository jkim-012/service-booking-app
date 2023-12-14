package com.example.bookingsystem.store.domain;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.store.dto.UpdateStoreDto;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  private String phone;

  @NotBlank
  private String location;

  @NotBlank
  private String zipcode;

  // operation hours
  @NotNull
  private Time openTime;
  @NotNull
  private Time closeTime;

  // shows if it is currently open or close
  private boolean isCurrentlyOpen;

  // store create & update - date and time
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // store status, if active, customers can book
  private boolean isActive;

  // mapping
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;



  // update store information
  public void updateInfo(UpdateStoreDto updateStoreDto) {
    if (updateStoreDto.getName() != null) {
      this.name = updateStoreDto.getName();
    }
    if (updateStoreDto.getDescription() != null) {
      this.description = updateStoreDto.getDescription();
    }
    if (updateStoreDto.getPhone() != null) {
      this.phone = updateStoreDto.getPhone();
    }
    if (updateStoreDto.getLocation() != null) {
      this.location = updateStoreDto.getLocation();
    }
    if (updateStoreDto.getZipcode() != null) {
      this.zipcode = updateStoreDto.getZipcode();
    }
    if (updateStoreDto.getOpenTime() != null) {
      this.openTime = updateStoreDto.getOpenTime();
    }
    if (updateStoreDto.getCloseTime() != null) {
      this.closeTime = updateStoreDto.getCloseTime();
    }

    this.updatedAt = LocalDateTime.now();
  }

  // update store status
  public void updateStatus(boolean isActive) {
    this.isActive = isActive;
  }
}
