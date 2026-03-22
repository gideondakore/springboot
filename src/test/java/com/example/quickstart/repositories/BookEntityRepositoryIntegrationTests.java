package com.example.quickstart.repositories;

import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.AuthorEntity;
import com.example.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(authorEntity);
        Book saveBook = underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(saveBook);
    }

    @Test
    void testThatMultipleBooksCanBeCreatedAndRecalled(){

        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();

        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();

        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();

        Book bookA = TestDataUtil.createTestBookA(authorEntityA);
        Book bookASave = underTest.save(bookA);

        Book bookB = TestDataUtil.createTestBookB(authorEntityB);
        Book bookBSave = underTest.save(bookB);

        Book bookC = TestDataUtil.createTestBookC(authorEntityC);
        Book bookCSave = underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookASave, bookBSave, bookCSave);
    }

    @Test
    void testThatBookCanBeUpdated(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(authorEntityA);

        underTest.save(bookA);
        bookA.setTitle("UPDATED");

        Book bookSave = underTest.save(bookA);
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookSave);
    }

    @Test
    void testThatBookCanBeDeleted(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();

        Book bookA = TestDataUtil.createTestBookA(authorEntityA);
        underTest.save(bookA);
        bookA.setTitle("UPDATED");

        underTest.delete(bookA);
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
