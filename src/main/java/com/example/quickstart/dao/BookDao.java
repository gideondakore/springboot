package com.example.quickstart.dao;

import com.example.quickstart.domain.Book;

import java.util.Optional;

public interface BookDao {

    void create(Book book);

    Optional<Book> findOne(String s);
}
