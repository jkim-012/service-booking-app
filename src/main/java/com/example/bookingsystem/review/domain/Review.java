package com.example.bookingsystem.review.domain;


import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.review.dto.UpdateReviewDto;
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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Double rate;

    // review create & update - date and time
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // mapping
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;


    // update review
    public void changeReviewDetails(UpdateReviewDto updateReviewDto) {
        if (updateReviewDto.getTitle() != null){
            this.title = updateReviewDto.getTitle();
        }
        if (updateReviewDto.getContent() != null){
            this.content = updateReviewDto.getContent();
        }
        if (updateReviewDto.getRate() != null){
            this.rate = updateReviewDto.getRate();
        }
    }
}
