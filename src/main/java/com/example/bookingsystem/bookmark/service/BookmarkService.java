package com.example.bookingsystem.bookmark.service;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;


public interface BookmarkService {
    BookmarkDetailDto createBookmark(Long businessId, String bookmarkName);
}
