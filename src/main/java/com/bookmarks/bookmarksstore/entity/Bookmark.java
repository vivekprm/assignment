package com.bookmarks.bookmarksstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue
    private long id;
    private String url;
    @Column(unique=true)
    @NotNull
    private String title;
    @ElementCollection
    private List<String> notes;
    @ElementCollection
    private List<String> tags;

    public Bookmark(){}

    public Bookmark(String title, String url, List<String> notes, List<String> tags) {
        this.title = title;
        this.url = url;
        this.notes = notes;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
