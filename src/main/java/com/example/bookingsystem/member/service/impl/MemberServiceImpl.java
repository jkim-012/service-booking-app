package com.example.bookingsystem.member.service.impl;

import com.example.bookingsystem.exception.EmailAlreadyExistException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.dto.NewMemberDto;
import com.example.bookingsystem.member.repository.MemberRepository;
import com.example.bookingsystem.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

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
}
