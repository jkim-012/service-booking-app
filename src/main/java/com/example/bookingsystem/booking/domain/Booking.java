package com.example.bookingsystem.booking.domain;

import com.example.bookingsystem.booking.dto.UpdateBookingDto;
import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.service.domain.ServiceItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column
    private String memo; //additional information

    @ManyToOne
    @JoinColumn(name = "service_item_id")
    private ServiceItem serviceItem;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    // change booking details (schedule date/time and memo)
    public void changeBookingInfo(UpdateBookingDto updateBookingDto) {
        if (updateBookingDto.getScheduledAt() != null){
            this.scheduledAt  = updateBookingDto.getScheduledAt();
        }
        if (updateBookingDto.getMemo() != null){
            this.memo = updateBookingDto.getMemo();
        }
    }

    // change booking status (cancel or complete)
    public void changeStatus(BookingStatus newStatus) {
        this.status = newStatus;
    }
}
