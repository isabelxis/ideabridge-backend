package com.ideabridge_backend.ideabridge_backend.dto;

import com.ideabridge_backend.ideabridge_backend.model.Urgency;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProblemCreateDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    private Urgency urgency = Urgency.MEDIUM;// Consider using Urgency enum
    private String budget;
    private String timeline;
}
