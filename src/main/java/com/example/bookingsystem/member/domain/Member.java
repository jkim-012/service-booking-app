package com.example.bookingsystem.member.domain;

import com.example.bookingsystem.store.domain.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
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
  private String email;
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
  private List<Store> storeList;

}
