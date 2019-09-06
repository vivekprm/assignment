package com.bookmarks.bookmarksstore.repository;

import com.bookmarks.bookmarksstore.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment, Integer> {
    Comment findByTitle(String title);
}
