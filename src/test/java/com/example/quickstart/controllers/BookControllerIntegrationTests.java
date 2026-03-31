package com.example.quickstart.controllers;

import com.example.quickstart.TestDataUtil;
import com.example.quickstart.domain.entities.AuthorEntity;
import com.example.quickstart.domain.entities.BookEntity;
import com.example.quickstart.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

class BookControllerIntegrationTests {
    @Autowired
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testThatSaveSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookEntity.setAuthor(null);

        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/"+bookEntity.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    void testThatCreateAuthorSuccessfullyReturnsHttpSaveBook() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookEntity.setAuthor(null);

        String bookJson = objectMapper.writeValueAsString(bookEntity);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/978-3-16-148410-3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-3-16-148410-3")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Beauty and the Beast")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").isEmpty()
        );
    }

    @Test
    void testThatListBooksReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                );
    }

    @Test
    void testThatListBooksReturnsListOfBooks() throws Exception {

        BookEntity bookEntity = TestDataUtil.createTestBookEntity();
        bookEntity.setAuthor(null);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookEntity.getIsbn())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$[0].title").value(bookEntity.getTitle())
                );

    }

    @Test
    void testThatGetBooksReturnsHttpStatus200Ok() throws Exception{
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    void testThatGetBooksReturnsHttpStatus404NotFound() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-0-306-40615-1748-28923")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    void testThatGetBooksReturnsBook() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookEntity.getTitle())
        );
    }

    @Test
    void testThatFullUpdateBookReturnsHttpStatus200NotFound() throws Exception{
        AuthorEntity authorEntity = TestDataUtil.createTestAuthorA();
        BookEntity bookEntity = TestDataUtil.createTestBookA(authorEntity);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        BookEntity bookEntityB = TestDataUtil.createTestBookB(authorEntityB);
        bookEntityB.setIsbn(bookEntity.getIsbn());

        String json = objectMapper.writeValueAsString(bookEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    void testThatFullUpdateUpdatesExistingBook() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookA(null);
        BookEntity savedBookEntity = bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);


        BookEntity bookEntityB = TestDataUtil.createTestBookB(null);


        String json = objectMapper.writeValueAsString(bookEntityB);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/"+savedBookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(savedBookEntity.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookEntityB.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(savedBookEntity.getAuthor())
        );

    }
}
