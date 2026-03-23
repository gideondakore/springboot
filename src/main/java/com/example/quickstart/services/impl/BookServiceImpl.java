package com.example.quickstart.services.impl;

import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.exceptions.BookNotFoundException;
import com.example.quickstart.repositories.AuthorRepository;
import com.example.quickstart.repositories.BookRepository;
import com.example.quickstart.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {

        bookEntity.setIsbn(isbn);
        if(bookEntity.getAuthor() != null && bookEntity.getAuthor().getId() != null){
            AuthorEntity authorEntity = authorRepository.findById(bookEntity.getAuthor().getId()).orElseThrow(() -> new BookNotFoundException("Author Not Found"));
            bookEntity.setAuthor(authorEntity);
        }
        return bookRepository.save(bookEntity);
    }
}
