package com.example.bookingsystem.bookmark.service;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import com.example.bookingsystem.member.domain.Member;

import java.util.List;


public interface BookmarkService {
    BookmarkDetailDto createBookmark(Long businessId, String bookmarkName, Member member);
    BookmarkDetailDto updateBookmark(Long bookmarkId, String newBookmarkName, Member member);
    void deleteBookmark(Long bookmarkId, Member member);
    List<BookmarkDetailDto> getAllBookmarks(Member member);

}
