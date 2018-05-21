package com.bookmarks.bookmarksstore.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class ViewMapper {

    public static ObjectMapper MAPPER = new ObjectMapper();

    public <T> T map(Object obj, Class<T> clazz) {
        MAPPER = MAPPER.configure(
                FAIL_ON_UNKNOWN_PROPERTIES, false);
        return MAPPER.convertValue(obj, clazz);
    }

    public <T> List<T> mapAll(Object obj, Class<T> clazz) {
        MAPPER = MAPPER.configure(
                FAIL_ON_UNKNOWN_PROPERTIES, false);
        return MAPPER.convertValue(obj, new TypeReference<List<T>>() {
        });
    }
}
