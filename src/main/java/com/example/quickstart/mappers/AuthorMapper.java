package com.example.quickstart.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper<A, B> {

    B mapTo(A a);
    A mapFrom(B b);
}
