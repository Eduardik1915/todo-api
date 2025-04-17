package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.service.TodoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
public class TodoControllerWebTest {

    TodoResponseDto task1, task2;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @BeforeEach
    void TaskList() {
        task1 = TodoResponseDto.builder().id(1L).title("Task1").completed(false).build();
        task2 = TodoResponseDto.builder().id(2L).title("Task2").completed(true).build();
    }


    @Test
    void getAllTodos_shouldReturnJsonList() throws Exception {
        List<TodoResponseDto> todos = List.of(task1, task2);

        Mockito.when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Task1"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    void getTodoById_shouldReturnTodo_whenExists() throws Exception {
        Mockito.when(todoService.getTodoById(1L)).thenReturn(task1);

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Task1"))
                .andExpect(jsonPath("$.completed").value(false));

    }

    @Test
    void getTodoById_shouldReturnNotFound_whenNotExists() throws Exception {
        Mockito.when(todoService.getTodoById(99L)).thenThrow(new EntityNotFoundException("Task with id 99 not found"));

        mockMvc.perform(get("/api/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTodoById_shouldReturnBadRequest_whenIdIsInvalid() throws Exception {
        // This test verifies that the controller returns a 400 Bad Request status
        // when a non-numeric ID is passed in the request path.
        // Although this is handled by Spring automatically, including this test
        // ensures that this behavior is explicitly covered and doesn't break unexpectedly.
        mockMvc.perform(get("/api/todos/invalid"))
                .andExpect(status().isBadRequest());
    }
}
