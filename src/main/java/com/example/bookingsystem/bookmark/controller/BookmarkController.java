package com.example.bookingsystem.bookmark.controller;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import com.example.bookingsystem.bookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // API endpoint for adding business bookmark
    @PostMapping("/bookmarks/{businessId}")
    public ResponseEntity<BookmarkDetailDto> createBookmark(
            @PathVariable Long businessId,
            @RequestParam String newBookmarkName) {

        BookmarkDetailDto bookmarkDetailDto = bookmarkService.createBookmark(businessId, newBookmarkName);
        return ResponseEntity.ok(bookmarkDetailDto);
    }

    // API endpoint for updating business bookmark
    @PatchMapping("/bookmarks/{bookmarkId}")
    public ResponseEntity<BookmarkDetailDto> updateBookmarkName(
            @PathVariable Long bookmarkId,
            @RequestParam String newBookmarkName) {

        BookmarkDetailDto bookmarkDetailDto = bookmarkService.updateBookmark(bookmarkId, newBookmarkName);
        return ResponseEntity.ok(bookmarkDetailDto);
    }

    // API endpoint for deleting business bookmark
    @DeleteMapping("/bookmarks/{bookmarkId}")
    public ResponseEntity<?> deleteBookmark(
            @PathVariable Long bookmarkId) {

        bookmarkService.deleteBookmark(bookmarkId);
        return ResponseEntity.ok("The bookmark is now deleted.");
    }

    // API endpoint for reading a list of saved bookmarks
    @GetMapping("/bookmarks/list")
    public ResponseEntity<List<BookmarkDetailDto>> getAllBookmarks(){

        List<BookmarkDetailDto> bookmarkList = bookmarkService.getAllBookmarks();
        return ResponseEntity.ok(bookmarkList);
    }

}
