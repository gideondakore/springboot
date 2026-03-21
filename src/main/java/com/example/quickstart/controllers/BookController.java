package com.example.quickstart.controllers;

import com.example.quickstart.domain.Book;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

    @GetMapping(path = "/books")
    public Book retrieveBook(){
        return Book.builder()
                .isbn("978-8-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody @Valid final Book book){
        log.info("Got book" + book.toString());
        return book;
    }
}
