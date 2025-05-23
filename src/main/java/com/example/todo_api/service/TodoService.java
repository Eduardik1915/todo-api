package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.dto.TodoUpdateDto;
import com.example.todo_api.entity.Todo;
import com.example.todo_api.mapper.TodoMapper;
import com.example.todo_api.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final String entityNotFound = "Task with id %d not found";

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoResponseDto> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::toResponseDto)
                .toList();
    }

    public TodoResponseDto getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(todoMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityNotFound, id)));
    }

    public TodoResponseDto createTodo(TodoCreateDto todoCreateDto) {
        Todo todo = todoMapper.toTodoFromCreateDto(todoCreateDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toResponseDto(savedTodo);
    }

    public TodoResponseDto updateTodo(Long id, TodoUpdateDto updatedTodo) {
        Todo foundTodo = todoRepository.findById(id)
                .map(todo -> todoMapper.todoFromUpdateDto(updatedTodo, todo))
                .orElseThrow(() -> new EntityNotFoundException(String.format(entityNotFound, id)));
        Todo savedTodo = todoRepository.save(foundTodo);
        return todoMapper.toResponseDto(savedTodo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(entityNotFound, id));
        }
        todoRepository.deleteById(id);
    }
}
