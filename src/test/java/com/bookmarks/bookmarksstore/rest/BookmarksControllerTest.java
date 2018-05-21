package com.bookmarks.bookmarksstore.rest;

import com.bookmarks.bookmarksstore.service.BookmarkService;
import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookmarksController.class)
public class BookmarksControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookmarkService service;

    @Test
    public void givenBookmarks_whenGetBookmarks_thenReturnJsonArray()
            throws Exception {
        List<String> notes = new ArrayList<>();
        notes.add("Author: \"Vivek\"");
        notes.add("Location:\"Noida\"");

        List<String> tags = new ArrayList<>();
        tags.add("Development");
        tags.add("Product");

        BookmarkDto bookmark = new BookmarkDto("TestBookmark", "http://www.google.com",
                notes, tags);

        List<BookmarkDto> allBookmarks = new ArrayList<>();
        allBookmarks.add(bookmark);

        given(service.getAllBookmarks()).willReturn(allBookmarks);

        mvc.perform(get("/api/bookmarks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(bookmark.getTitle()))
                );
    }
}