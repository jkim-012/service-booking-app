package com.example.bookingsystem.member.service;

import com.example.bookingsystem.member.dto.LoginDto;
import com.example.bookingsystem.member.dto.NewMemberDto;

public interface MemberService {
  void register(NewMemberDto newMemberDto);
  boolean login(LoginDto loginDto);

}
