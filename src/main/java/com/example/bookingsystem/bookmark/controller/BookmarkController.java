package com.example.bookingsystem.bookmark.controller;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import com.example.bookingsystem.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // API endpoint for adding business bookmark
    @PostMapping("/bookmark/{businessId}")
    public ResponseEntity<BookmarkDetailDto> createBookmark(
            @PathVariable Long businessId,
            @RequestParam String bookmarkName) {

        BookmarkDetailDto bookmarkDetailDto = bookmarkService.createBookmark(businessId, bookmarkName);
        return ResponseEntity.ok(bookmarkDetailDto);
    }

}
