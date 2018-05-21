package com.bookmarks.bookmarksstore.service;

import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;

import java.util.List;

public interface BookmarkService {
    List<BookmarkDto> getAllBookmarks();

    void saveBookmark(BookmarkDto bookmarkDto);

    void deleteBookmark(Long id);

    BookmarkDto findById(Long id);
}
