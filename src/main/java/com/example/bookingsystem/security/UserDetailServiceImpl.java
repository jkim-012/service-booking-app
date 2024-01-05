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
        return createUserDetails(member);

    }

  private UserDetails createUserDetails(Member member) {
    String role = "ROLE_" + member.getRole();
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);

    return new org.springframework.security.core.userdetails.User(
            member.getEmail(),
            member.getPassword(),
            Collections.singleton(grantedAuthority)
    );
  }
}
