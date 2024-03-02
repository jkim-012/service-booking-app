package com.example.bookingsystem.member.domain;

import com.example.bookingsystem.booking.domain.Booking;
import com.example.bookingsystem.bookmark.domain.Bookmark;
import com.example.bookingsystem.business.domain.Business;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email; // login username
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SEX sex;

    // mapping
    @OneToMany(mappedBy = "member")
    private List<Business> businessList;

    @OneToMany(mappedBy = "member")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarkList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleNameWithPrefix = "ROLE_" + role.toString();
//        System.out.println(roleNameWithPrefix);
        return List.of(new SimpleGrantedAuthority(roleNameWithPrefix));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
