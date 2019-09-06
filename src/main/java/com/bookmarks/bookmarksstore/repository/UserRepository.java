package com.bookmarks.bookmarksstore.repository;

import com.bookmarks.bookmarksstore.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByFirstName(String firstName);

    User[] findByLastName(String lastName);
}
