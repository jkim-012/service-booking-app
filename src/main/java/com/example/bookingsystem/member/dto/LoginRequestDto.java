package com.example.bookingsystem.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

  @Email(message = "이메일을 형식에 맞게 입력해 주세요.")
  @NotBlank(message = "이메일은 필수 항목입니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 항목입니다.")
  private String password;
}
