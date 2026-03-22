package com.example.quickstart.repositories;

import com.example.quickstart.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int age);

    //Approach 1
//    @Query(value = "SELECT a FROM AuthorEntity a WHERE a.age > ?1", nativeQuery = false)
//    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);

    //Approach 2
    @Query("SELECT a FROM AuthorEntity a WHERE a.age > :age")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(@Param("age") int age);
}
