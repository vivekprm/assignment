package com.bookmarks.bookmarksstore.service;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import com.bookmarks.bookmarksstore.repository.BookmarkRepository;
import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;
import com.bookmarks.bookmarksstore.service.exceptions.RecordDoesNotExistException;
import com.bookmarks.bookmarksstore.service.exceptions.RecordExistsException;
import com.bookmarks.bookmarksstore.utils.ViewMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class BookmarkServiceImplTest {
    @TestConfiguration
    static class BookmarkServiceImplTestContextConfiguration {

        @Bean
        public BookmarkService bookmarkService() {
            return new BookmarkServiceImpl();
        }
        @Bean
        public ViewMapper viewMapper() {
            return new ViewMapper();
        }
    }

    @Autowired
    private BookmarkService bookmarkService;

    @MockBean
    private BookmarkRepository bookmarkRepository;

    @Before
    public void setUp() {
        Bookmark bookmark = getBookmark("TestBookmark");
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(bookmark);

        Bookmark bookmark1 = getBookmark("FirstBookmark");

        Bookmark bookmark2 = getBookmark("SecondBookmark");

        Mockito.when(bookmarkRepository.findAll())
                .thenReturn(bookmarks);
        Mockito.when(bookmarkRepository.findByTitle("FirstBookmark"))
                .thenReturn(bookmark1);
        Mockito.when(bookmarkRepository.findOne(20l))
                .thenReturn(bookmark2);
    }

    private Bookmark getBookmark(String title) {
        List<String> notes = new ArrayList<>();
        notes.add("Author: \"Vivek\"");
        notes.add("Location:\"Noida\"");

        List<String> tags = new ArrayList<>();
        tags.add("Development");
        tags.add("Product");

        Bookmark bookmark = new Bookmark(title, "http://www.google.com",
                notes, tags);
        bookmark.setId(20l);
        return bookmark;
    }

    private BookmarkDto getBookmarkDto(String title) {
        List<String> notes = new ArrayList<>();
        notes.add("Author: \"Vivek\"");
        notes.add("Location:\"Noida\"");

        List<String> tags = new ArrayList<>();
        tags.add("Development");
        tags.add("Product");

        return new BookmarkDto(title, "http://www.google.com",
                notes, tags);
    }

    @Test
    public void givenBookmarks_whenGetAllBookmarks_thenReturnBookmarkList() {
        List<BookmarkDto> bookmarkDtoList = bookmarkService.getAllBookmarks();
        assertThat(bookmarkDtoList.size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenBookmarkWithoutTitle_whenSaveBookmark_thenThrowsException(){
        BookmarkDto bookmark = getBookmarkDto(null);
        bookmarkService.saveBookmark(bookmark);
    }

    @Test(expected = RecordExistsException.class)
    public void givenBookmark_whenSaveBookmarkWithSameTitle_thenThrowsException(){
        BookmarkDto bookmark1 = getBookmarkDto("FirstBookmark");
        bookmarkService.saveBookmark(bookmark1);
        BookmarkDto bookmark2 = getBookmarkDto("FirstBookmark");
        bookmarkService.saveBookmark(bookmark2);
    }

    @Test(expected = RecordDoesNotExistException.class)
    public void givenNonExistingBookmarkId_whenFindById_thenThrowsException(){
        bookmarkService.findById(22l);
    }

    @Test(expected = RecordDoesNotExistException.class)
    public void givenNonExistingBookmarkId_whenDeleteBookmark_thenThrowsException(){
        bookmarkService.deleteBookmark(22l);
    }

    @Test
    public void givenBookmark_saveAndFindById_thenReturnBookmark(){
        BookmarkDto bookmark = getBookmarkDto("SecondBookmark");
        bookmarkService.saveBookmark(bookmark);
        BookmarkDto bookmarkDto = bookmarkService.findById(20l);
        assertThat(bookmarkDto.getId(), is(20l));
    }
}
