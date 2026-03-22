package com.example.quickstart.services;

import com.example.quickstart.domain.entities.AuthorEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
