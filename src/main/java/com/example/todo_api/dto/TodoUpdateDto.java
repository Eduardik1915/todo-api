package com.example.todo_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoUpdateDto {

    @Schema(description = "Title of the task", example = "Buy milk")
    @NotBlank(message = "{title.not.blank}")
    @Size(min = 3, max = 255, message = "{title.incorrect.length}")
    private String title;

    @Schema(description = "Details about the task", example = "Don't forget to get skimmed milk")
    private String description;

    @Schema(description = "Flag indicating whether the task is completed", example = "false")
    private Boolean completed;
}
