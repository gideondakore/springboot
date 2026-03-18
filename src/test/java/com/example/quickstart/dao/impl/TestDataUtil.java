package com.example.quickstart.dao.impl;

import com.example.quickstart.domain.Author;
import com.example.quickstart.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}

    static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Jesse Benz")
                .age(23)
                .build();
    }

    static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Andrew Casse")
                .age(93)
                .build();
    }

    static Book createTestBook() {
        return Book.builder()
                .isbn("978-0-306-40615-7")
                .title("Beauty and the Beast")
                .authorId(1L)
                .build();
    }
}
