package com.bookmarks.bookmarksstore.rest;

import com.bookmarks.bookmarksstore.rest.exceptions.RecordAlreadyExistsException;
import com.bookmarks.bookmarksstore.rest.exceptions.RecordNotFoundException;
import com.bookmarks.bookmarksstore.service.BookmarkService;
import com.bookmarks.bookmarksstore.service.dto.BookmarkDto;
import com.bookmarks.bookmarksstore.service.exceptions.RecordDoesNotExistException;
import com.bookmarks.bookmarksstore.service.exceptions.RecordExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarksController {
    @Autowired
    private BookmarkService bookmarkService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public List<BookmarkDto> getBookmarks(){
        return bookmarkService.getAllBookmarks();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveBookmark(@Valid @RequestBody BookmarkDto bookmarkDto){
        try{
            bookmarkService.saveBookmark(bookmarkDto);
        }
        catch (RecordExistsException ex){
            throw new RecordAlreadyExistsException(ex);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BookmarkDto getBookmarkById(@PathVariable Long id) {
        BookmarkDto rep = new BookmarkDto();
        rep.setId(id);
        rep = bookmarkService.findById(id);
        try {
            rep = bookmarkService.findById(id);
        } catch (RecordDoesNotExistException ex) {
            throw new RecordNotFoundException(ex);
        }
        return rep;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookmark(@PathVariable Long id) {
        try {
            bookmarkService.deleteBookmark(id);
        } catch (RecordDoesNotExistException ex) {
            throw new RecordNotFoundException(ex);
        }
    }
}
