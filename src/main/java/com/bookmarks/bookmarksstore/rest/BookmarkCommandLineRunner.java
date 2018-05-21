package com.bookmarks.bookmarksstore.rest;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import com.bookmarks.bookmarksstore.repository.BookmarkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * To insert sample data.
 */
@Component
public class BookmarkCommandLineRunner implements CommandLineRunner {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkCommandLineRunner(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Map<String, List<String>> titleNotesMap = new HashMap<>();
        Map<String, List<String>> titleTagsMap = new HashMap<>();

        List<String> notes1 = new ArrayList<>();
        notes1.add("Author: \"Vivek\"");
        notes1.add("Location:\"Noida\"");
        titleNotesMap.put("FirstBookmark", notes1);

        List<String> tags1 = new ArrayList<>();
        tags1.add("Development");
        tags1.add("Product");
        titleTagsMap.put("FirstBookmark", tags1);

        List<String> notes2 = new ArrayList<>();
        notes2.add("Author: \"Rahul\"");
        notes2.add("Location:\"Hyderabad\"");
        titleNotesMap.put("SecondBookmark", notes2);

        List<String> tags2 = new ArrayList<>();
        tags2.add("QA");
        tags2.add("Product");
        titleTagsMap.put("SecondBookmark", tags2);

        List<String> notes3 = new ArrayList<>();
        notes3.add("Author: \"Ravi\"");
        notes3.add("Location:\"Mumbai\"");
        titleNotesMap.put("ThirdBookmark", notes3);

        List<String> tags3 = new ArrayList<>();
        tags3.add("Security");
        tags3.add("Testing");
        titleTagsMap.put("ThirdBookmark", tags3);

        Stream.of("FirstBookmark", "SecondBookmark", "ThirdBookmark").forEach(title ->
                bookmarkRepository.save(
                        new Bookmark(title, "http://www.google.com",
                                titleNotesMap.get(title), titleTagsMap.get(title)))
        );
        bookmarkRepository.findAll().forEach(System.out::println);
    }
}
