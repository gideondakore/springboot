package com.example.quickstart.dao.impl;

import com.example.quickstart.domain.Author;
import com.example.quickstart.domain.Book;

public class TestDataUtil {
    private TestDataUtil(){}

    static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
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
