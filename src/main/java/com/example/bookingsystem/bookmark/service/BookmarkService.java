package com.example.bookingsystem.bookmark.service;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;

import java.util.List;


public interface BookmarkService {
    BookmarkDetailDto createBookmark(Long businessId, String bookmarkName);
    BookmarkDetailDto updateBookmark(Long bookmarkId, String newBookmarkName);
    void deleteBookmark(Long bookmarkId);
    List<BookmarkDetailDto> getAllBookmarks();

}
