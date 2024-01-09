package com.example.bookingsystem.review.domain;


import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.service.domain.ServiceItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "service_item_id")
    private ServiceItem serviceItem;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


}
