package com.example.bookingsystem.member.domain;

import com.example.bookingsystem.business.domain.Business;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

  @NotBlank
  private String email; // login username
  @NotBlank
  private String password;

  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  private String displayName;

  @NotNull
  private LocalDate dateOfBirth;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  private Role role;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  private SEX sex;

  // mapping
  @OneToMany(mappedBy = "member")
  private List<Business> storeList;

}
