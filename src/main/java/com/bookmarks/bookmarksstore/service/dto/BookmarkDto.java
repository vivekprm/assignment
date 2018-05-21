package com.bookmarks.bookmarksstore.service.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.net.URL;
import java.util.List;

public class BookmarkDto {
    private long id;
    @NotEmpty
    @Length(min = 5, max = 140)
    private String url;
    @NotEmpty
    @Length(min = 5, max = 140)
    private String title;
    private List<String> notes;
    private List<String> tags;

    public BookmarkDto(String title, String url, List<String> notes, List<String> tags) {
        this.url = url;
        this.title = title;
        this.notes = notes;
        this.tags = tags;
    }

    public BookmarkDto() {

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
