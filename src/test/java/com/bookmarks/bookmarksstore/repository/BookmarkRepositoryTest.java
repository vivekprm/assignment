package com.bookmarks.bookmarksstore.repository;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookmarkRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    public void whenFindByTitle_thenReturnBookmark() {
        // given
        List<String> notes = new ArrayList<>();
        notes.add("Author: \"Vivek\"");
        notes.add("Location:\"Noida\"");

        List<String> tags = new ArrayList<>();
        tags.add("Development");
        tags.add("Product");

        Bookmark bookmark = new Bookmark("TestBookmark", "http://www.google.com",
                notes, tags);
        entityManager.persist(bookmark);
        entityManager.flush();

        // when
        Bookmark found = bookmarkRepository.findByTitle(bookmark.getTitle());

        // then
        assertThat(found.getTitle()).isEqualTo(bookmark.getTitle());
    }
}
