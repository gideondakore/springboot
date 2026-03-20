package com.example.quickstart;

import com.example.quickstart.domain.Author;
import com.example.quickstart.domain.Book;

public class TestDataUtil {
    
    private TestDataUtil(){}

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Jesse Benz")
                .age(23)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Andrew Casse")
                .age(93)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-0-306-40615-1")
                .title("Beauty and the Beast")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-0-306-40615-2")
                .title("Tom & Jerry")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-0-306-40615-3")
                .title("The Lion King")
                .author(author)
                .build();
    }


}
