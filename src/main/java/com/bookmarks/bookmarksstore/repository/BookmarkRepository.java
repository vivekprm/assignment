package com.bookmarks.bookmarksstore.repository;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark findByTitle(String title);
}
