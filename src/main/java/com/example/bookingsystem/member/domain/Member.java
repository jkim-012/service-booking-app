package com.example.bookingsystem.member.domain;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.bookmark.domain.Bookmark;
import com.example.bookingsystem.business.domain.Business;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
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
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String email; // login username
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false)
  private String displayName;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private SEX sex;

  // mapping
  @OneToMany(mappedBy = "member")
  private List<Business> storeList;

  @OneToMany(mappedBy = "member")
  private List<Booking> bookingList;

  @OneToMany(mappedBy = "member")
  private List<Bookmark> bookmarkList;

}
