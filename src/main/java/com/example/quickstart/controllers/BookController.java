package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.BookDto;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.exceptions.BookNotFoundException;
import com.example.quickstart.mappers.impl.BookMapper;
import com.example.quickstart.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable @Valid String isbn, @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity bookSave = bookService.createBook(isbn, bookEntity);
        BookDto bookDtoSave = bookMapper.mapTo(bookSave);
        return new ResponseEntity<>(bookDtoSave, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<BookDto> listBooks(){
       List<BookEntity> bookEntity = bookService.findAll();
       return bookEntity.stream().map(bookMapper::mapTo).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") String isbn){
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        return bookEntity.map(book -> ResponseEntity.ok(bookMapper.mapTo(book))
        ).orElseThrow(
                () -> new BookNotFoundException("Book Not Found.")
        );
    }

}
