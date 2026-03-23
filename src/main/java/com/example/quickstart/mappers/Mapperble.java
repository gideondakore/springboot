package com.example.quickstart.mappers;

public interface Mapperble<A, B> {

    B mapTo(A a);
    A mapFrom(B b);
}
