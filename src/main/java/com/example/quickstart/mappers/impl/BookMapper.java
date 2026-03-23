package com.example.quickstart.mappers.impl;

import com.example.quickstart.domain.dto.BookDto;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.mappers.Mapperble;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends Mapperble<BookEntity, BookDto> {

    @Override
    BookDto mapTo(BookEntity bookEntity);

    @Override
    BookEntity mapFrom(BookDto bookDto);
}
