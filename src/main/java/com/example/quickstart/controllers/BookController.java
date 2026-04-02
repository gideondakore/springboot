package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.BookDto;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.exceptions.BookNotFoundException;
import com.example.quickstart.mappers.impl.BookMapper;
import com.example.quickstart.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<BookDto> save(@PathVariable String isbn, @RequestBody BookDto bookDto){
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        bookEntity.setIsbn(isbn);
        boolean exist = bookService.isExists(isbn);
        BookEntity bookSave = bookService.createUpdateBook(isbn, bookEntity);
        BookDto bookDtoSave = bookMapper.mapTo(bookSave);

        if(exist){
            return new ResponseEntity<>(bookDtoSave, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(bookDtoSave, HttpStatus.CREATED);
        }

    }

    @GetMapping()
    public Page<BookDto> listBooks(Pageable pageable){
       Page<BookEntity> bookEntity = bookService.findAll(pageable);
       return bookEntity.map(bookMapper::mapTo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") String isbn){
        Optional<BookEntity> bookEntity = bookService.findOne(isbn);
        return bookEntity.map(book -> ResponseEntity.ok(bookMapper.mapTo(book))
        ).orElseThrow(
                () -> new BookNotFoundException("Book Not Found.")
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> partialUpdate(@PathVariable("id") String isbn, @RequestBody BookDto bookDto){

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity bookSave = bookService.partialUpdate(isbn, bookEntity);
        BookDto bookDtoSave = bookMapper.mapTo(bookSave);

        return new ResponseEntity<>(bookDtoSave, HttpStatus.OK);

    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){
        bookService.delete(isbn);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
