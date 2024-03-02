package com.example.bookingsystem.member.service;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.dto.LoginRequestDto;
import com.example.bookingsystem.member.dto.LoginResponseDto;
import com.example.bookingsystem.member.dto.NewMemberDto;

public interface MemberService {
    void register(NewMemberDto newMemberDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
    void logout(String token, Member member);
}
