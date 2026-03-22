package com.example.quickstart.repositories;


import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class) // This is not necessary it is already added in the @SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest = underTest;
    }

    @Test
    void testThatAuthorCanBeCreatedAndRecalled(){

        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity authorEntityASave = underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        AuthorEntity authorEntityBSave = underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtil.createTestAuthorC();
        AuthorEntity authorEntityCSave = underTest.save(authorEntityC);

        Iterable<AuthorEntity> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(authorEntityASave, authorEntityBSave, authorEntityCSave);

    }

    @Test
    void testThatAuthorCanBeUpdated(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntityA);
        authorEntityA.setName("UPDATED");
        AuthorEntity authorEntitySave = underTest.save(authorEntityA);
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntitySave);

    }

    @Test
    void testThatAuthorCanBeDeleted(){
        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        underTest.save(authorEntityA);
        authorEntityA.setName("DELETED");
        underTest.deleteById(authorEntityA.getId());
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    void testThatGetAuthorsWithAgeLessThan(){
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity testAuthorEntityASave = underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        AuthorEntity testAuthorEntityBSave = underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        AuthorEntity testAuthorEntityCSave = underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.ageLessThan(90);
        assertThat(result).containsExactly(testAuthorEntityASave, testAuthorEntityBSave);
    }

    @Test
    void testThatGetAuthorsWithAgeGreaterThan(){
        AuthorEntity testAuthorEntityA = TestDataUtil.createTestAuthorA();
        AuthorEntity testAuthorEntityASave = underTest.save(testAuthorEntityA);
        AuthorEntity testAuthorEntityB = TestDataUtil.createTestAuthorB();
        AuthorEntity testAuthorEntityBSave = underTest.save(testAuthorEntityB);
        AuthorEntity testAuthorEntityC = TestDataUtil.createTestAuthorC();
        AuthorEntity testAuthorEntityCSave = underTest.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(90);
        assertThat(result).containsExactly(testAuthorEntityCSave);
    }
}
