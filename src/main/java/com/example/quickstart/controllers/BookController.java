package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.BookDto;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.mappers.impl.BookMapper;
import com.example.quickstart.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookMapper bookMapper;
    private BookService bookService;

    public BookController(BookMapper bookMapper, BookService bookService){
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }


    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") @Valid String isbn, @RequestBody BookDto bookDto){
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

}
