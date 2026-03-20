package com.example.quickstart.repositories;


import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class) // This is not necessary it is already added in the @SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest = underTest;
    }

    @Test
    void testThatAuthorCanBeCreatedAndRecalled(){

        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorASave = underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorBSave = underTest.save(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        Author authorCSave = underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorASave, authorBSave, authorCSave);

    }

    @Test
    void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        authorA.setName("UPDATED");
        Author authorSave = underTest.save(authorA);
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorSave);

    }

    @Test
    void testThatAuthorCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        authorA.setName("DELETED");
        underTest.deleteById(authorA.getId());
        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    void testThatGetAuthorsWithAgeLessThan(){
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        Author testAuthorASave = underTest.save(testAuthorA);
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        Author testAuthorBSave = underTest.save(testAuthorB);
        Author testAuthorC = TestDataUtil.createTestAuthorC();
        Author testAuthorCSave = underTest.save(testAuthorC);

        Iterable<Author> result = underTest.ageLessThan(90);
        assertThat(result).containsExactly(testAuthorASave, testAuthorBSave);
    }

    @Test
    void testThatGetAuthorsWithAgeGreaterThan(){
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        Author testAuthorASave = underTest.save(testAuthorA);
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        Author testAuthorBSave = underTest.save(testAuthorB);
        Author testAuthorC = TestDataUtil.createTestAuthorC();
        Author testAuthorCSave = underTest.save(testAuthorC);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(90);
        assertThat(result).containsExactly(testAuthorCSave);
    }
}
