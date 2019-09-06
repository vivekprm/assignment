package com.bookmarks.bookmarksstore.rest;

import com.bookmarks.bookmarksstore.entity.Bookmark;
import com.bookmarks.bookmarksstore.entity.Comment;
import com.bookmarks.bookmarksstore.entity.User;
import com.bookmarks.bookmarksstore.repository.BookmarkRepository;
import com.bookmarks.bookmarksstore.repository.CommentRepository;
import com.bookmarks.bookmarksstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

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

        setUpUsers();

        setUpComments();
    }

    private void setUpComments() {
        commentRepository.deleteAll();
        commentRepository.save(new Comment("Test1", "This is first test comment."));
        commentRepository.save(new Comment("Test2", "This is second test comment."));

        // fetch all customers
        System.out.println("Comments found with findAll():");
        System.out.println("-------------------------------");
        for (Comment comment : commentRepository.findAll()) {
            System.out.println(comment);
        }
        System.out.println();

        // fetch an individual comment
        System.out.println("Comment found with findByTitle('Test1'):");
        System.out.println("--------------------------------");
        System.out.println(commentRepository.findByTitle("Test1"));
    }

    private void setUpUsers() {
        userRepository.deleteAll();

        // save a couple of customers
        userRepository.save(new User("Alice", "Smith"));
        userRepository.save(new User("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(userRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (User user : userRepository.findByLastName("Smith")) {
            System.out.println(user);
        }
    }
}
