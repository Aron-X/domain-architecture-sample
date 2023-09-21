package com.aron.sample.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Component
public class SuppressObjectMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public SuppressObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T readValue(InputStream inputStream, Class<T> clazz) {
        if (Objects.isNull(inputStream)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(inputStream, clazz);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(content, valueTypeRef);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T readValue(String content, Class<T> clazz) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(content, clazz);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T readValueTo(InputStream inputStream, TypeReference<T> valueTypeRef) {
        if (Objects.isNull(inputStream)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(inputStream, valueTypeRef);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<String> readListString(String content) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(content, new TypeReference<List<String>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> List<T> readListValue(String content) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(content, new TypeReference<List<T>>() {
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> List<T> readListValue(String content, TypeReference<List<T>> typeReference) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return this.objectMapper.readValue(content, typeReference);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T convertValue(Object object, TypeReference<?> typeReference) {
        if (Objects.isNull(object)) {
            return null;
        }
        return (T) this.objectMapper.convertValue(object, typeReference);
    }
}
