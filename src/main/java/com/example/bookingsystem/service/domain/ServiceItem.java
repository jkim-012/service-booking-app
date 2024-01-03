package com.example.bookingsystem.service.domain;

import com.example.bookingsystem.business.domain.Business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ServiceItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Integer duration;

  @ManyToOne
  @JoinColumn(name = "business_id")
  private Business business;

}
