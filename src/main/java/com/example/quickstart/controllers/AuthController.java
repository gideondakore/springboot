package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthController {

    private AuthorService authorService;

    public AuthController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/")
    public AuthorDto createAuthor(@RequestBody @Valid AuthorDto author){
       return authorService.createAuthor(author);
    }
}
