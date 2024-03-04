package com.example.bookingsystem.bookmark.controller;

import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import com.example.bookingsystem.bookmark.service.BookmarkService;
import com.example.bookingsystem.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // API endpoint for adding business bookmark
    @PostMapping("/customer/bookmarks/{businessId}")
    public ResponseEntity<BookmarkDetailDto> createBookmark(
            @PathVariable Long businessId,
            @RequestParam String newBookmarkName,
            @AuthenticationPrincipal Member member) {

        BookmarkDetailDto bookmarkDetailDto = bookmarkService.createBookmark(businessId, newBookmarkName, member);
        return ResponseEntity.ok(bookmarkDetailDto);
    }

    // API endpoint for updating bookmark name
    @PatchMapping("/customer/bookmarks/{bookmarkId}")
    public ResponseEntity<BookmarkDetailDto> updateBookmarkName(
            @PathVariable Long bookmarkId,
            @RequestParam String newBookmarkName,
            @AuthenticationPrincipal Member member) {

        BookmarkDetailDto bookmarkDetailDto = bookmarkService.updateBookmark(bookmarkId, newBookmarkName, member);
        return ResponseEntity.ok(bookmarkDetailDto);
    }

    // API endpoint for deleting bookmarked business
    @DeleteMapping("/customer/bookmarks/{bookmarkId}")
    public ResponseEntity<?> deleteBookmark(
            @PathVariable Long bookmarkId,
            @AuthenticationPrincipal Member member) {

        bookmarkService.deleteBookmark(bookmarkId, member);
        return ResponseEntity.ok("The bookmark is now deleted.");
    }

    // API endpoint for reading a list of saved bookmarks
    @GetMapping("/customer/bookmarks/list")
    public ResponseEntity<List<BookmarkDetailDto>> getAllBookmarks(
            @AuthenticationPrincipal Member member){

        List<BookmarkDetailDto> bookmarkList = bookmarkService.getAllBookmarks(member);
        return ResponseEntity.ok(bookmarkList);
    }

}
