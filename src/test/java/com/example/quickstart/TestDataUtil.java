package com.example.quickstart;

import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.domain.entities.BookEntity;

public class TestDataUtil {
    
    private TestDataUtil(){}

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .name("Jesse Benz")
                .age(23)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .name("Andrew Casse")
                .age(93)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-0-306-40615-1")
                .title("Beauty and the Beast")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-0-306-40615-2")
                .title("Tom & Jerry")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-0-306-40615-3")
                .title("The Lion King")
                .authorEntity(authorEntity)
                .build();
    }


}
