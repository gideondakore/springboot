package com.example.quickstart.repositories;

import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.Author;
import com.example.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class BookRepositoryIntegrationTests {

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest){
        this.underTest = underTest;
    }

    @Test
    void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);
        Book saveBook = underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(saveBook);
    }

    @Test
    void testThatMultipleBooksCanBeCreatedAndRecalled(){

        Author authorA = TestDataUtil.createTestAuthorA();

        Author authorB = TestDataUtil.createTestAuthorB();

        Author authorC = TestDataUtil.createTestAuthorC();

        Book bookA = TestDataUtil.createTestBookA(authorA);
        Book bookASave = underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(authorB);
        Book bookBSave = underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(authorC);
        Book bookCSave = underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookASave, bookBSave, bookCSave);
    }

    @Test
    void testThatBookCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(authorA);

        underTest.save(bookA);
        bookA.setTitle("UPDATED");

        Book bookSave = underTest.save(bookA);
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookSave);
    }

    @Test
    void testThatBookCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(authorA);
        underTest.save(bookA);
        bookA.setTitle("UPDATED");

        underTest.delete(bookA);
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
