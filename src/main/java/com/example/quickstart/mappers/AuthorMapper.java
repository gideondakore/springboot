package com.example.quickstart.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface Mapperble<A, B> {

    B mapTo(A a);
    A mapFrom(B b);
}
