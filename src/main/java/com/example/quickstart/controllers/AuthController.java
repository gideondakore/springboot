package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.mappers.impl.AuthorMapper;
import com.example.quickstart.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;


    @PostMapping("/")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity authorEntitySave = authorService.createAuthor(authorEntity);
        AuthorDto authorDto = authorMapper.mapTo(authorEntitySave);
        return new ResponseEntity<>(authorDto,HttpStatus.CREATED);
    }
}
