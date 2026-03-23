package com.example.quickstart.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer age;
}
