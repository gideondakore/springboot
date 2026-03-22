package com.example.quickstart.repositories;

import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

class BookEntityRepositoryIntegrationTests {

    private BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest){
        this.underTest = underTest;
    }

    @Test
    void testThatBookCanBeCreatedAndRecalled(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        BookEntity saveBookEntity = underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(saveBookEntity);
    }

    @Test
    void testThatMultipleBooksCanBeCreatedAndRecalled(){

        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();

        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();

        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntityA);
        BookEntity bookEntityASave = underTest.save(bookEntityA);

        BookEntity bookEntityB = TestDataUtil.createTestBookB(authorEntityB);
        BookEntity bookEntityBSave = underTest.save(bookEntityB);

        BookEntity bookEntityC = TestDataUtil.createTestBookC(authorEntityC);
        BookEntity bookEntityCSave = underTest.save(bookEntityC);

        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(bookEntityASave, bookEntityBSave, bookEntityCSave);
    }

    @Test
    void testThatBookCanBeUpdated(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntityA);

        underTest.save(bookEntityA);
        bookEntityA.setTitle("UPDATED");

        BookEntity bookEntitySave = underTest.save(bookEntityA);
        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntitySave);
    }

    @Test
    void testThatBookCanBeDeleted(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntityA);
        underTest.save(bookEntityA);
        bookEntityA.setTitle("UPDATED");

        underTest.delete(bookEntityA);
        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isEmpty();
    }
}
