package com.example.quickstart.dao.impl;

import com.example.quickstart.domain.Author;

public class TestDataUtil {
    private TestDataUtil(){}

    static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }
}
