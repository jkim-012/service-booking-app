package com.example.bookingsystem.bookmark.repository;

import com.example.bookingsystem.bookmark.domain.Bookmark;
import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> getAllByMemberId(Long memberId);
}
