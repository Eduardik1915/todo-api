package com.example.todo_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TodoResponseDto {

    @Schema(description = "Unique identifier of the task", example = "1")
    private Long id;

    @Schema(description = "Title of the task", example = "Buy milk")
    private String title;

    @Schema(description = "Details about the task", example = "Don't forget to get skimmed milk")
    private String description;

    @Schema(description = "Flag indicating whether the task is completed", example = "false")
    private Boolean completed;

    @Schema(description = "Date and time when the task was created", example = "2025-04-14T10:45:00")
    private LocalDateTime createdAt;


}
