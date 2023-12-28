package com.example.bookingsystem.business.domain;

import com.example.bookingsystem.business.dto.NewBusinessDto;
import com.example.bookingsystem.business.dto.UpdateAddressDto;
import com.example.bookingsystem.business.dto.UpdateBasicInfoDto;
import com.example.bookingsystem.business.dto.UpdateHoursDto;
import com.example.bookingsystem.member.domain.Member;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
public class Business {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private String phone;

  // address
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Province province;
  @Column(nullable = false)
  private String city;
  @Column(nullable = false)
  private String streetAddress;
  @Column(nullable = false)
  private String postalCode;

  // operation hours
  @Column(nullable = false)
  private LocalTime openTime;
  @Column(nullable = false)
  private LocalTime closeTime;

  // current open status
  @Column
  private boolean isCurrentlyOpen;
  // current business status
  @Column
  private boolean isActive;

  // business create & update - date and time
  @Column(nullable = false)
  @CreatedDate
  private LocalDateTime createdAt;
  @Column
  @LastModifiedDate
  private LocalDateTime updatedAt;

  // mapping
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  // create business
  public static Business create(NewBusinessDto newBusinessDto) {
    return Business.builder()
        .name(newBusinessDto.getName())
        .description(newBusinessDto.getDescription())
        .phone(newBusinessDto.getPhone())
        .province(newBusinessDto.getProvince())
        .city(newBusinessDto.getCity())
        .streetAddress(newBusinessDto.getStreetAddress())
        .postalCode(newBusinessDto.getPostalCode())
        .openTime(newBusinessDto.getOpenTime()) //HH:mm:ss
        .closeTime(newBusinessDto.getCloseTime()) //HH:mm:ss
        .build();
  }

  // update business address information
  public void updateAddress(UpdateAddressDto.Request request) {
    if (request.getProvince() != null) {
      this.province = request.getProvince();
    }
    if (request.getCity() != null) {
      this.city = request.getCity();
    }
    if (request.getStreetAddress() != null) {
      this.streetAddress = request.getStreetAddress();
    }
    if (request.getPostalCode() != null) {
      this.postalCode = request.getPostalCode();
    }
  }

  // update business status
  public void updateActiveStatus(boolean isActive) {
    this.isActive = isActive;
  }

  // update open status
  public void updateOpenStatus(boolean isCurrentlyOpen) {
    this.isCurrentlyOpen = isCurrentlyOpen;
  }

  public void updateHours(UpdateHoursDto updateHoursDto) {
    this.openTime = updateHoursDto.getOpenTime();
    this.closeTime = updateHoursDto.getCloseTime();
  }

  public void updateBasicInfo(UpdateBasicInfoDto updateBasicInfoDto) {
    if (updateBasicInfoDto.getName() != null){
      this.name = updateBasicInfoDto.getName();
    }
    if (updateBasicInfoDto.getDescription() != null){
      this.description = updateBasicInfoDto.getDescription();
    }
    if (updateBasicInfoDto.getPhone() != null){
      this.description = updateBasicInfoDto.getDescription();
    }
  }
}
