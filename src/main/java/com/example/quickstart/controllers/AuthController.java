package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.mappers.impl.AuthorMapper;
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

    private AuthorMapper authorMapper;

    public AuthController(AuthorService authorService, AuthorMapper authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("")
    public AuthorDto createAuthor(@RequestBody @Valid AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(savedAuthorEntity);
    }
}
