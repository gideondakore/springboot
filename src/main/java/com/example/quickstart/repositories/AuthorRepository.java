package com.example.quickstart.repositories;

import com.example.quickstart.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Iterable<Author> ageLessThan(int age);

    //Approach 1
//    @Query(value = "SELECT a FROM Author a WHERE a.age > ?1", nativeQuery = false)
//    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);

    //Approach 2
    @Query("SELECT a FROM Author a WHERE a.age > :age")
    Iterable<Author> findAuthorsWithAgeGreaterThan(@Param("age") int age);
}
