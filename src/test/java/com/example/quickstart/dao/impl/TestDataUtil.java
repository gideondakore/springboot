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

    static Book createTestBookA() {
        return Book.builder()
                .isbn("978-0-306-40615-1")
                .title("Beauty and the Beast")
                .authorId(1L)
                .build();
    }

    static Book createTestBookB() {
        return Book.builder()
                .isbn("978-0-306-40615-2")
                .title("Tom & Jerry")
                .authorId(2L)
                .build();
    }

    static Book createTestBookC() {
        return Book.builder()
                .isbn("978-0-306-40615-3")
                .title("The Lion King")
                .authorId(3L)
                .build();
    }


}
