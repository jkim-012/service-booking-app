package com.example.bookingsystem.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

  @Email(message = "Please enter a valid email address.")
  @NotBlank(message = "Email is a required field.")
  private String email;

  @NotBlank(message = "Password is a required field.")
  @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters.")
  private String password;
}
