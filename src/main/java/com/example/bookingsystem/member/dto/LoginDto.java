package com.example.bookingsystem.member.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

  @Email(message = "이메일을 형식에 맞게 입력해 주세요.")
  @NotBlank(message = "이메일은 필수 항목입니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 항목입니다.")
  private String password;
}
