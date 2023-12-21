package com.example.bookingsystem.business.dto;

import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.domain.Province;
import com.example.bookingsystem.member.domain.Member;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessDetailDto {

  private Long id;
  private String name;
  private String description;
  private String phone;

  // address
  private Province province;
  private String city;
  private String streetAddress;
  private String postalCode;

  // operation hours
  private Time openTime;
  private Time closeTime;

  // current open status
  private boolean isCurrentlyOpen;
  // current business status
  private boolean isActive;

  // store create & update - date and time
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // Business owner
  private Member member;

  // Business entity -> BusinessDetailDto
  public static BusinessDetailDto of(Business business) {
    return BusinessDetailDto.builder()
        .id(business.getId())
        .name(business.getName())
        .phone(business.getPhone())
        .province(business.getProvince())
        .city(business.getCity())
        .streetAddress(business.getStreetAddress())
        .postalCode(business.getPostalCode())
        .openTime(business.getOpenTime())
        .closeTime(business.getCloseTime())
        .isCurrentlyOpen(business.isCurrentlyOpen())
        .isActive(business.isActive())
        .createdAt(business.getCreatedAt())
        .updatedAt(business.getUpdatedAt())
        .member(business.getMember())
        .build();
  }
}
