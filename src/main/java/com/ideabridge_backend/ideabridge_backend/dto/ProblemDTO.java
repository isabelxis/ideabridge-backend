package com.ideabridge_backend.ideabridge_backend.dto;

import com.ideabridge_backend.ideabridge_backend.model.ProblemStatus;
import com.ideabridge_backend.ideabridge_backend.model.Urgency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {
    private String id;
    private String ownerId;
    private String title;
    private String description;
    private String category;
    private Urgency urgency;
    private ProblemStatus status; // Consider using ProblemStatus enum
    private String budget;
    private String timeline;
    private List<String> attachments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
