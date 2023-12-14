package com.example.bookingsystem.member.repository;


import com.example.bookingsystem.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  int countByEmail(String email);
}
