package com.example.bookingsystem.member.dto;

import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.domain.SEX;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String displayName;
    private LocalDate dateOfBirth;
    private SEX sex;

    public static MemberDetailDto of(Member member){
        return MemberDetailDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .displayName(member.getDisplayName())
                .dateOfBirth(member.getDateOfBirth())
                .sex(member.getSex())
                .build();
    }
}
