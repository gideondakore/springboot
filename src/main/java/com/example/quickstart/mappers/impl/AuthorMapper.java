package com.example.quickstart.mappers.impl;

import com.example.quickstart.domain.dto.AuthorDto;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.mappers.Mapperble;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends Mapperble<AuthorEntity, AuthorDto> {

    @Override
    AuthorDto mapTo(AuthorEntity authorEntity);

    @Override
    AuthorEntity mapFrom(AuthorDto authorDto);
}
