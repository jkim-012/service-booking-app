package com.example.bookingsystem.bookmark.repository;

import com.example.bookingsystem.bookmark.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
