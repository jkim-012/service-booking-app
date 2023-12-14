package com.example.bookingsystem.member.dto;

import com.example.bookingsystem.member.domain.Role;
import com.example.bookingsystem.member.domain.SEX;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMemberDto {

  @Email(message = "Please enter a valid email address.")
  @NotBlank(message = "Email is a required field.")
  private String email;

  @NotBlank(message = "Password is a required field.")
  @Size(min = 5, max = 20, message = "Password should be between 5 and 20 characters.")
  private String password;

  @NotBlank(message = "First name is a required field.")
  private String firstName;

  @NotBlank(message = "Last name is a required field.")
  private String lastName;

  @NotBlank(message = "Display name is a required field.")
  @Size(min = 5, max = 10, message = "Member name should be between 5 and 10 characters.")
  private String displayName;

  @NotNull(message = "Date of Birth is a required field.")
  private LocalDate dateOfBirth;

  @NotNull(message = "Role is a required field.")
  @Enumerated(value = EnumType.STRING)
  private Role role;

  @NotNull(message = "Sex is a required field.")
  @Enumerated(value = EnumType.STRING)
  private SEX sex;
}
