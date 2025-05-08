package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.dto.TodoUpdateDto;
import com.example.todo_api.entity.Todo;

public class TodoFactory {
    private static final Long DEFAULT_ID = 1L;

    public static Todo existingTodo() {
        return Todo.builder().id(DEFAULT_ID).title("Existing todo").completed(false).build();
    }

    public static Todo createTodo() {
        return Todo.builder().id(DEFAULT_ID).title("Create todo").completed(false).build();
    }

    public static Todo updatedTodo() {
        return Todo.builder().id(DEFAULT_ID).title("Updated todo").completed(true).build();
    }

    public static TodoCreateDto createDto() {
        return TodoCreateDto.builder().title("Create todo").completed(false).build();
    }

    public static TodoUpdateDto updateDto() {
        return TodoUpdateDto.builder().title("Updated todo").completed(true).build();
    }

    public static TodoResponseDto responseDtoExisting() {
        return TodoResponseDto.builder().id(DEFAULT_ID).title("Existing todo").completed(false).build();
    }

    public static TodoResponseDto responseDtoCreate() {
        return TodoResponseDto.builder().id(DEFAULT_ID).title("Create todo").completed(false).build();
    }

    public static TodoResponseDto responseDtoUpdated() {
        return TodoResponseDto.builder().id(DEFAULT_ID).title("Updated todo").completed(true).build();
    }
}
