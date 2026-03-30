package com.example.quickstart.services.impl;

import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.repositories.AuthorRepository;
import com.example.quickstart.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        Iterable<AuthorEntity> authorEntities = authorRepository.findAll();
        return StreamSupport.stream(authorEntities.spliterator(), false).toList();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean exists(Long id) {
       return authorRepository.existsById(id);
    }

}
