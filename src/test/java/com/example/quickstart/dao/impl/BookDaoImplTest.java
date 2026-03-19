package com.example.quickstart.dao.impl;

import com.example.quickstart.domain.Author;
import com.example.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @InjectMocks
    private AuthorDaoImpl authorDao;

    @Test
    public void testThatCreateBookGenerateCorrectSql(){
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);


        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-0-306-40615-1"),
                eq("Beauty and the Beast"),
                eq(1L)
        );
    }

    @Test
    void testThatFindOneGenerateTheCorrectSql(){

        underTest.findOne("978-0-306-40615-7");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.any(BookDaoImpl.BookRowMapper.class),
                eq("978-0-306-40615-7")
                );
    }

    @Test
    void testThatFindManyGeneratesCorrectSql(){
        underTest.find();

        verify(jdbcTemplate).query(eq("SELECT isbn, title, author_id FROM books"), ArgumentMatchers.any(BookDaoImpl.BookRowMapper.class));

    }

    @Test
    void testThatUpdateBookGenerateCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.update("978-0-306-40615-1", book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId(),
                "978-0-306-40615-1"
                );
    }

    @Test
    void testThatDeleteBookGenerateCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.delete(book.getIsbn());
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn = ?",
                "978-0-306-40615-1"
        );
    }

}
