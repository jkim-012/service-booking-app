package com.example.bookingsystem.security;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.domain.Role;
import com.example.bookingsystem.member.repository.MemberRepository;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Member member = memberRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

    // if exist
    return new User(member.getEmail(), member.getPassword(), getAuthorities(member.getRole()));


  }

  private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
    return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
  }
}
