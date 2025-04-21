package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoCreateDto;
import com.example.todo_api.dto.TodoResponseDto;
import com.example.todo_api.dto.TodoUpdateDto;
import com.example.todo_api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "Find all tasks", description = "Returns all tasks",
            responses = {
                    @ApiResponse(
                            description = "Successful operation",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TodoResponseDto.class))))
            })
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        List<TodoResponseDto> allTodos = todoService.getAllTodos();
        return ResponseEntity.ok(allTodos);
    }


    @Operation(summary = "Find task by id", description = "Returns a task by its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the task",
                            required = true,
                            example = "1"
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "Successful operation",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoResponseDto.class))
                    ),
                    @ApiResponse(
                            description = "Task not found",
                            responseCode = "404",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "Task with id 1 not found"))
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        TodoResponseDto todoById = todoService.getTodoById(id);
        return ResponseEntity.ok(todoById);
    }


    @Operation(summary = "Create new task", description = "Creates new task and returns it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO containing data for the new task",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoCreateDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = "Task successfully created and returned in response",
                            responseCode = "201",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TodoResponseDto.class))
                    ),
                    @ApiResponse(
                            description = "There is some invalid value",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "Title can't be blank"))
                    )
            })
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody @Valid TodoCreateDto todoCreateDto) {
        TodoResponseDto todo = todoService.createTodo(todoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }


    @Operation(summary = "Update task", description = "Update existing task",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "DTO with updated task data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TodoUpdateDto.class)
                    )
            ),
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the task update",
                            required = true,
                            example = "1"
                    )
            },
            responses = {
                    @ApiResponse(
                            description = "Task successfully updated and returned in response",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TodoResponseDto.class))
                    ),
                    @ApiResponse(
                            description = "There is some invalid value",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = "Title can't be blank"))
                    ),
                    @ApiResponse(
                            description = "Task not found",
                            responseCode = "404",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "Task with id 1 not found"))
                    )
            })
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody @Valid TodoUpdateDto updatedTodo) {
        TodoResponseDto todoResponseDto = todoService.updateTodo(id, updatedTodo);
        return ResponseEntity.ok(todoResponseDto);
    }


    @Operation(summary = "Delete task", description = "Deletes a task from the database based on its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID of the task to delete",
                            required = true,
                            example = "1"
                    )
            },
            responses = {
                    @ApiResponse(description = "Task successfully deleted",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Task not found",
                            responseCode = "404",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "Task with id 1 not found")))
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
