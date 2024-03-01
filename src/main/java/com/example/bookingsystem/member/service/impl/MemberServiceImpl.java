package com.example.bookingsystem.member.service.impl;

import com.example.bookingsystem.exception.EmailAlreadyExistException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.dto.LoginRequestDto;
import com.example.bookingsystem.member.dto.LoginResponseDto;
import com.example.bookingsystem.member.dto.NewMemberDto;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.member.service.MemberService;
import com.example.bookingsystem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public void register(NewMemberDto newMemberDto) {

    // email duplication check
    if (memberRepository.countByEmail(newMemberDto.getEmail()) > 0){
      throw new EmailAlreadyExistException(
          newMemberDto.getEmail() + " is already registered. Please use a different email address.");
    }

    // password encoding
    String encodedPw = passwordEncoder.encode(newMemberDto.getPassword());

    // new member object
    Member member = Member.builder()
        .email(newMemberDto.getEmail())
        .password(encodedPw)
        .firstName(newMemberDto.getFirstName())
        .lastName(newMemberDto.getLastName())
        .displayName(newMemberDto.getDisplayName())
        .dateOfBirth(newMemberDto.getDateOfBirth())
        .role(newMemberDto.getRole())
        .sex(newMemberDto.getSex())
        .build();

    // save
    memberRepository.save(member);
  }

  @Override
  public LoginResponseDto login(LoginRequestDto loginRequestDto) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
      Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();

      // create JWT token
      String jwtToken = jwtService.generateToken(member);

      return LoginResponseDto.builder()
              .token(jwtToken)
              .build();
  }
}
