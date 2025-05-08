package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerWebTest {

    TodoResponseDto responseDto1, responseDto2;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void TaskList() {
        responseDto1 = TodoResponseDto.builder().id(1L).title("Task1").completed(false).build();
        responseDto2 = TodoResponseDto.builder().id(2L).title("Task2").completed(true).build();
    }

    @Test
    void getAllTodos_returns200AndTodoList() throws Exception {
        List<TodoResponseDto> todos = List.of(responseDto1, responseDto2);

        Mockito.when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Task1"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    void getTodoById_returns200AndResponseTodo_whenIdExists() throws Exception {
        Mockito.when(todoService.getTodoById(1L)).thenReturn(responseDto1);

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Task1"))
                .andExpect(jsonPath("$.completed").value(false));

    }

    @Test
    void getTodoById_returns404_whenTodoNotFound() throws Exception {
        Mockito.when(todoService.getTodoById(99L)).thenThrow(new EntityNotFoundException("Task with id 99 not found"));

        mockMvc.perform(get("/api/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTodoById_shouldReturnBadRequest_whenIdIsInvalid() throws Exception {
        // when a non-numeric ID is passed in the request path.
        // Although this is handled by Spring automatically, including this test
        // ensures that this behavior is explicitly covered and doesn't break unexpectedly.
        mockMvc.perform(get("/api/todos/invalid"))
                .andExpect(status().isBadRequest());
    }      // This test verifies that the controller returns a 400 Bad Request status


    @Test
    void createTodo_returns201AndResponseDto_whenInputIsValid() throws Exception {
        TodoCreateDto newTodo = TodoCreateDto.builder()
                .title("To the cosmos")
                .description("Fly to the moon")
                .build();

        String json = objectMapper.writeValueAsString(newTodo);

        Mockito.when(todoService.createTodo(Mockito.any(TodoCreateDto.class))).thenReturn(responseDto1);

        mockMvc.perform(post("/api/todos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Task1"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "ta"})
    void createTodo_returns400_whenTitleIsInvalid(String invalidTitle) throws Exception {
        TodoCreateDto invalidTodo = TodoCreateDto.builder()
                .title(invalidTitle)
                .description("Fly to the moon")
                .build();

        String json = objectMapper.writeValueAsString(invalidTodo);

        mockMvc.perform(post("/api/todos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
