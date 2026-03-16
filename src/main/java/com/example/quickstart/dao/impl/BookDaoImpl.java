package com.example.quickstart.dao.impl;

import com.example.quickstart.dao.BookDao;
import com.example.quickstart.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void create(Book book){
        jdbcTemplate.update("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

}
