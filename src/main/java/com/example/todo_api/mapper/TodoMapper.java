package com.example.todo_api.mapper;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.dto.TodoUpdateDto;
import com.example.todo_api.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoResponseDto toResponseDto(Todo todo);

    Todo toTodoFromCreateDto(TodoCreateDto todoCreateDto);

    Todo updateTodoFromDto(TodoUpdateDto updateDto, @MappingTarget Todo todo);
}
