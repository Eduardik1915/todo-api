package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.entity.Todo;
import com.example.todo_api.mapper.TodoMapper;
import com.example.todo_api.repository.TodoRepository;
import com.example.todo_api.service.TodoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TodoServiceUnitTest {

    private Todo task1, task2;
    private TodoResponseDto dto1, dto2;

    @Mock
    TodoRepository todoRepository;

    @Mock
    TodoMapper todoMapper;

    @InjectMocks
    TodoService todoService;

    @BeforeEach
    void initTodos() {
        task1 = Todo.builder().id(1L).title("Task1").completed(false).build();
        task2 = Todo.builder().id(2L).title("Task2").completed(true).build();

        dto1 = TodoResponseDto.builder().id(1L).title("Task1").completed(false).build();
        dto2 = TodoResponseDto.builder().id(2L).title("Task2").completed(true).build();
    }

    @Test
    void getAllTodos_shouldReturnTodoList() {
        Mockito.when(todoRepository.findAll()).thenReturn(List.of(task1,task2));
        Mockito.when(todoMapper.toResponseDto(task1)).thenReturn(dto1);
        Mockito.when(todoMapper.toResponseDto(task2)).thenReturn(dto2);

        List<TodoResponseDto> result = todoService.getAllTodos();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Task1", result.get(0).getTitle());
        Assertions.assertEquals(true, result.get(1).getCompleted());
    }

    @Test
    void getTodoById_shouldReturnTodo_whenExists() {
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(task1));
        Mockito.when(todoMapper.toResponseDto(task1)).thenReturn(dto1);

        TodoResponseDto result = todoService.getTodoById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Task1", result.getTitle());
    }

    @Test
    void getTodoById_shouldThrow_whenNotExists() {
        Mockito.when(todoRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> todoService.getTodoById(99L));

        Assertions.assertEquals("Task with id 99 not found", exception.getMessage());
    }
}
