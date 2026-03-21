package com.example.quickstart;

import com.example.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTests {

    @Test
    void testThatObjectMapperCanCreateJsonFromJavaObject() throws JacksonException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = Book.builder()
                        .isbn("978-8-13-478627-5")
                        .title("The Enigma of Eternity")
                        .author("Aria Montgomery")
                        .yearPublished("2005")
                        .build();

        String result = objectMapper.writeValueAsString(book);
        assertThat(result).isEqualTo("{\"isbn\":\"978-8-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}");

    }

    @Test
    void testThatObjectMapperCanCreateJavaObjecFromJsonObject() throws JacksonException{
        Book book = Book.builder()
                .isbn("978-8-13-478627-5")
                .title("The Enigma of Eternity")
                .author("Aria Montgomery")
                .yearPublished("2005")
                .build();

        String json = "{\"shifo\":\"master\",\"isbn\":\"978-8-13-478627-5\",\"title\":\"The Enigma of Eternity\",\"author\":\"Aria Montgomery\",\"year\":\"2005\"}";
        final ObjectMapper objectMapper = new ObjectMapper();
        Book result = objectMapper.readValue(json, Book.class);

        assertThat(result).isEqualTo(book);

    }
}
