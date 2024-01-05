package com.example.bookingsystem.service.domain;

import javax.persistence.*;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.service.dto.UpdateServiceItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ServiceItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // service basic info - name, desc, price, duration
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private Double price;
  @Column
  private Integer duration;

  @ManyToOne
  @JoinColumn(name = "business_id")
  private Business business;

  @OneToMany(mappedBy = "serviceItem")
  private List<Booking> bookingList;


  // update service information (name, description, price, duration)
  public void changeServiceInfo(UpdateServiceItemDto updateServiceItemDto) {
    if (updateServiceItemDto.getName() != null){
      this.name = updateServiceItemDto.getName();
    }
    if (updateServiceItemDto.getDescription() != null){
      this.description = updateServiceItemDto.getDescription();
    }
    if (updateServiceItemDto.getPrice() != null){
      this.price = updateServiceItemDto.getPrice();
    }
    if (updateServiceItemDto.getDuration() != null){
      this.duration = updateServiceItemDto.getDuration();
    }
  }
}
