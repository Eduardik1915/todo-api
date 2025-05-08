package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.dto.TodoUpdateDto;
import com.example.todo_api.entity.Todo;
import com.example.todo_api.mapper.TodoMapper;
import com.example.todo_api.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceUnitTest {

    @Mock
    TodoRepository todoRepository;

    @Mock
    TodoMapper todoMapper;

    @InjectMocks
    TodoService todoService;

    @Test
    void getAllTodos_shouldReturnTodoList() {
        Todo existingTodo = TodoFactory.existingTodo();
        TodoResponseDto responseDto = TodoFactory.responseDtoExisting();

        when(todoRepository.findAll()).thenReturn(List.of(existingTodo));
        when(todoMapper.toResponseDto(existingTodo)).thenReturn(responseDto);

        List<TodoResponseDto> actualResponse = todoService.getAllTodos();

        Assertions.assertEquals(1, actualResponse.size());
        Assertions.assertEquals("Existing todo", actualResponse.get(0).getTitle());
        Assertions.assertFalse(actualResponse.get(0).getCompleted());
    }

    @Test
    void getTodoById_shouldReturnTodo_whenExists() {
        Long id = 1L;
        Todo existingTodo = TodoFactory.existingTodo();
        TodoResponseDto responseDto = TodoFactory.responseDtoExisting();

        when(todoRepository.findById(id)).thenReturn(Optional.of(existingTodo));
        when(todoMapper.toResponseDto(existingTodo)).thenReturn(responseDto);

        TodoResponseDto actualResponse = todoService.getTodoById(id);

        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(id, actualResponse.getId());
        Assertions.assertEquals("Existing todo", actualResponse.getTitle());
    }

    @Test
    void getTodoById_shouldThrow_whenNotExists() {
        Long id = 99L;

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> todoService.getTodoById(id));

        Assertions.assertEquals("Task with id 99 not found", exception.getMessage());
    }

    @Test
    void createTodo_shouldReturnSavedToDo() {
        Long id = 1L;
        Todo createTodo = TodoFactory.createTodo();
        TodoCreateDto createDto = TodoFactory.createDto();
        TodoResponseDto responseDto = TodoFactory.responseDtoCreate();

        when(todoMapper.toTodoFromCreateDto(createDto)).thenReturn(createTodo);
        when(todoRepository.save(createTodo)).thenReturn(createTodo);
        when(todoMapper.toResponseDto(createTodo)).thenReturn(responseDto);

        TodoResponseDto actualResponse = todoService.createTodo(createDto);

        Assertions.assertEquals(id, actualResponse.getId());
        Assertions.assertEquals("Create todo", actualResponse.getTitle());
        Assertions.assertFalse(actualResponse.getCompleted());
    }


    @Test
    void updateTodo_shouldReturnUpdatedToDo() {
        Long id = 1L;
        Todo existingTodo = TodoFactory.existingTodo();
        TodoUpdateDto updateDto = TodoFactory.updateDto();
        Todo updatedTodo = TodoFactory.updatedTodo();
        TodoResponseDto expectedResponse = TodoFactory.responseDtoUpdated();

        when(todoRepository.findById(id)).thenReturn(Optional.of(existingTodo));
        when(todoMapper.todoFromUpdateDto(updateDto, existingTodo)).thenReturn(updatedTodo);
        when(todoRepository.save(updatedTodo)).thenReturn(updatedTodo);
        when(todoMapper.toResponseDto(updatedTodo)).thenReturn(expectedResponse);

        TodoResponseDto actualResponse = todoService.updateTodo(id, updateDto);

        Assertions.assertEquals(id, actualResponse.getId());
        Assertions.assertEquals("Updated todo", actualResponse.getTitle());
        Assertions.assertTrue(actualResponse.getCompleted());
    }

    @Test
    void updateTodo_shouldThrowEntityNotFound_whenTodoNotFound() {
        Long id = 99L;
        TodoUpdateDto updateTodo = TodoFactory.updateDto();

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> todoService.updateTodo(id, updateTodo));

        Assertions.assertEquals("Task with id 99 not found", exception.getMessage());
    }

    @Test
    void deleteTodo_shouldDoNothing_whenTodoIsFound() {
        Long id = 1L;

        when(todoRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(todoRepository).deleteById(id);

        todoService.deleteTodo(id);

        verify(todoRepository, times(1)).existsById(any());
        verify(todoRepository, times(1)).deleteById(any());
    }

    @Test
    void deleteTodo_shouldThrowEntityNotFound_whenTodoNotFound() {
        Long id = 99L;

        when(todoRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> todoService.deleteTodo(id));

        Assertions.assertEquals("Task with id 99 not found", exception.getMessage());

    }
}
