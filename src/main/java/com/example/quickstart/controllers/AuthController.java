package com.example.quickstart.controllers;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.exceptions.AuthorNotFoundException;
import com.example.quickstart.mappers.impl.AuthorMapper;
import com.example.quickstart.services.AuthorService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;


    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createUpdateBook(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<AuthorDto> listAuthors(){
        List<AuthorEntity> authorsEntity = authorService.findAll();
        return authorsEntity.stream().map(authorMapper::mapTo).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id){
        Optional<AuthorEntity> authorEntity = authorService.findOne(id);

        return authorEntity.map(author -> ResponseEntity.ok(authorMapper.mapTo(author)))
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){

        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity saveAuthorEntity = authorService.createUpdateBook(authorEntity);
        return new ResponseEntity<>(
                authorMapper.mapTo(saveAuthorEntity),
                HttpStatus.OK
        );
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<AuthorDto> partialUpdate(@PathVariable Long id, @RequestBody AuthorDto authorDto){

        if(!authorService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthor = authorService.partialUpdate(id, authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthor), HttpStatus.OK);

    }
}
