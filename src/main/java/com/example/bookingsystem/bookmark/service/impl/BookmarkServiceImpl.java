package com.example.bookingsystem.bookmark.service.impl;

import com.example.bookingsystem.bookmark.domain.Bookmark;
import com.example.bookingsystem.bookmark.dto.BookmarkDetailDto;
import com.example.bookingsystem.bookmark.repository.BookmarkRepository;
import com.example.bookingsystem.bookmark.service.BookmarkService;
import com.example.bookingsystem.business.domain.Business;
import com.example.bookingsystem.business.repository.BusinessRepository;
import com.example.bookingsystem.exception.BookmarkNotFoundException;
import com.example.bookingsystem.exception.BusinessNotFoundException;
import com.example.bookingsystem.exception.UnauthorizedUserException;
import com.example.bookingsystem.member.domain.Member;
import com.example.bookingsystem.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final BusinessRepository businessRepository;

    @Override
    public BookmarkDetailDto createBookmark(Long businessId, String bookmarkName) {

        // get logged in member
        Member member = getLoggedInMember();
        // get business to be added
        Business business = getBusiness(businessId);

        // create bookmark
        Bookmark bookmark =  Bookmark.builder()
                .name(bookmarkName)
                .member(member)
                .business(business)
                .build();
        // save
        bookmarkRepository.save(bookmark);
        return BookmarkDetailDto.of(bookmark);
    }

    @Override
    @Transactional
    public BookmarkDetailDto updateBookmark(Long bookmarkId, String newBookmarkName) {
        // get logged in member
        Member member = getLoggedInMember();
        // get bookmark
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(()-> new BookmarkNotFoundException("Bookmark not found with ID: " + bookmarkId));
        // check member's authority
        if(!bookmark.getMember().equals(member)){
            throw  new UnauthorizedUserException("Unauthorized: You do not have permission to update the booking status.");
        }
        // update
        bookmark.updateName(newBookmarkName);
        return BookmarkDetailDto.of(bookmark);
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        // get logged in member
        Member member = getLoggedInMember();
        // get bookmark
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(()-> new BookmarkNotFoundException("Bookmark not found with ID: " + bookmarkId));
        // check member's authority
        if(!bookmark.getMember().equals(member)){
            throw  new UnauthorizedUserException("Unauthorized: You do not have permission to delete the booking status.");
        }
        // delete
        bookmarkRepository.delete(bookmark);
    }

    @Override
    public List<BookmarkDetailDto> getAllBookmarks() {
        // get logged in member
        Long memberId = getLoggedInMember().getId();
        List<Bookmark> bookmarkList = bookmarkRepository.getAllByMemberId(memberId);
        return bookmarkList.stream().map(BookmarkDetailDto::of).collect(Collectors.toList());
    }


    private Business getBusiness(Long businessId) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(()-> new BusinessNotFoundException("Business not found with ID: " + businessId));
        return business;
    }

    private Member getLoggedInMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return member;
    }
}
