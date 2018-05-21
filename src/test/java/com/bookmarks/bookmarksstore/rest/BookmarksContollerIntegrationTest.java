package com.bookmarks.bookmarksstore.rest;

import com.bookmarks.bookmarksstore.BookmarksStoreApplication;
import com.bookmarks.bookmarksstore.repository.BookmarkRepository;
import com.bookmarks.bookmarksstore.service.BookmarkService;
import com.bookmarks.bookmarksstore.service.BookmarkServiceImpl;
import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;
import com.bookmarks.bookmarksstore.utils.ViewMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BookmarksStoreApplication.class)
@WebAppConfiguration
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class BookmarksContollerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BookmarkService bookmarkService;

    private MockMvc mvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Before
    public void setupMockMvc() {
        cleanup();
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void afterTest(){
        cleanup();
    }

    @Test
    public void givenBookmarks_whenGetBookmarks_thenStatus200()
            throws Exception {

        createTestBookmark();

        mvc.perform(get("/api/bookmarks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title", is("TestBookmark")));
    }

    private void createTestBookmark() {
        List<String> notes = new ArrayList<>();
        notes.add("Author: \"Vivek\"");
        notes.add("Location:\"Noida\"");

        List<String> tags = new ArrayList<>();
        tags.add("Development");
        tags.add("Product");

        BookmarkDto bookmark = new BookmarkDto("TestBookmark", "http://www.google.com",
                notes, tags);
        bookmarkService.saveBookmark(bookmark);
    }

    public void cleanup(){
        bookmarkRepository.deleteAll();
    }
}
