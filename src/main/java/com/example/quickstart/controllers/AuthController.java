package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.mappers.impl.AuthorMapper;
import com.example.quickstart.services.AuthorService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    

    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<AuthorDto> listAuthors(){
        List<AuthorEntity> authorsEntity = authorService.findAll();
        return authorsEntity.stream().map(authorMapper::mapTo).toList();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable Long id){
        AuthorEntity authorEntity = authorService.findOne(id);
        return authorMapper.mapTo(authorEntity);
    }
}
