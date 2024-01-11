package com.example.bookingsystem.bookmark.dto;

import com.example.bookingsystem.bookmark.domain.Bookmark;
import com.example.bookingsystem.business.dto.BusinessDetailDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDetailDto {

    private Long bookmarkId;
    private String name;
    BusinessDetailDto business;


    // Bookmark entity -> BookmarkDetailDto
    public static BookmarkDetailDto of(Bookmark bookmark) {
        return BookmarkDetailDto.builder()
                .bookmarkId(bookmark.getId())
                .name(bookmark.getName())
                .business(BusinessDetailDto.of(bookmark.getBusiness()))
                .build();
    }
}
