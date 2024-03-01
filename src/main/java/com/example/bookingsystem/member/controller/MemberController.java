package com.example.bookingsystem.member.controller;

import com.example.bookingsystem.member.dto.LoginRequestDto;
import com.example.bookingsystem.member.dto.LoginResponseDto;
import com.example.bookingsystem.member.dto.NewMemberDto;
import com.example.bookingsystem.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  // API endpoint for registering
  @PostMapping("/member")
  public ResponseEntity<?> register(@RequestBody @Valid NewMemberDto newMemberDto){

    memberService.register(newMemberDto);
    return ResponseEntity.ok("Registration successfully completed.");
  }

  // API endpoint for login
  @PostMapping("/member/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
    return ResponseEntity.ok(memberService.login(loginRequestDto));
  }

}
