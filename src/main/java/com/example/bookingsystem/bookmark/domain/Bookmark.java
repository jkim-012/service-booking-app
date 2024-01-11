package com.example.bookingsystem.bookmark.domain;


import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //customer can set the name of a bookmark

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "business_id")
    Business business;
}
