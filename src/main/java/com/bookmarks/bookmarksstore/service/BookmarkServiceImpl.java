package com.bookmarks.bookmarksstore.service;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import com.bookmarks.bookmarksstore.repository.BookmarkRepository;
import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;
import com.bookmarks.bookmarksstore.service.exceptions.RecordDoesNotExistException;
import com.bookmarks.bookmarksstore.service.exceptions.RecordExistsException;
import com.bookmarks.bookmarksstore.utils.ViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private ViewMapper viewMapper;

    @Override
    public List<BookmarkDto> getAllBookmarks() {
        List<Bookmark> bookmarkList = bookmarkRepository.findAll();
        List<BookmarkDto> result = new ArrayList<>();
        if (bookmarkList == null) {
            return result;
        }
        for (Bookmark bookmark : bookmarkList) {
            result.add(viewMapper.map(bookmark, BookmarkDto.class));
        }
        return result;
    }

    @Override
    public void saveBookmark(BookmarkDto bookmarkDto) {
        String title = bookmarkDto.getTitle();
        if(title == null){
            throw new IllegalArgumentException("Bookmark title can't be null.");
        }
        Bookmark bookmark = bookmarkRepository.findByTitle(title);
        if(bookmark != null){
            throw new RecordExistsException("Bookmark with title " + title + " already exists.",
                    new RuntimeException());
        }
        bookmarkRepository.save(viewMapper.map(bookmarkDto, Bookmark.class));
    }

    @Override
    public void deleteBookmark(Long id) {
        Bookmark bookmark = bookmarkRepository.findOne(id);
        if(bookmark == null){
            throw new RecordDoesNotExistException("Bookmark with id " + id + " not found.",
                    new RuntimeException());
        }
        bookmarkRepository.delete(id);
    }

    @Override
    public BookmarkDto findById(Long id) {
        Bookmark bookmark = bookmarkRepository.findOne(id);
        if(bookmark == null){
            throw new RecordDoesNotExistException("Bookmark with id " + id + " not found.",
                    new RuntimeException());
        }
        return viewMapper.map(bookmark, BookmarkDto.class);
    }
}
