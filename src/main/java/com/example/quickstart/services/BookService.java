package com.example.quickstart.services;

import com.example.quickstart.domain.entities.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity bookEntity);
}
