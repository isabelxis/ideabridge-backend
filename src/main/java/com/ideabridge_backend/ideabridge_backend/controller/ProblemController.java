package com.ideabridge_backend.ideabridge_backend.controller;

import com.ideabridge_backend.ideabridge_backend.dto.ProblemCreateDTO;
import com.ideabridge_backend.ideabridge_backend.dto.ProblemDTO;
import com.ideabridge_backend.ideabridge_backend.model.ProblemStatus;
import com.ideabridge_backend.ideabridge_backend.service.ProblemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity<ProblemDTO> createProblem(
            Authentication authentication,
            @Valid @RequestBody ProblemCreateDTO dto) {
        // Implementation for creating a new problem
        String ownerId = authentication.getName();
        ProblemDTO problem = problemService.createProblem(ownerId, dto);
        return ResponseEntity.ok(problem);
    }

    @GetMapping
    public ResponseEntity<List<ProblemDTO>> getAllProblems(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status
    ) {
        List<ProblemDTO> problems;

        if (search != null && !search.isEmpty()) {
            problems = problemService.searchProblems(search);
        } else if (category != null && !category.isEmpty()) {
            problems = problemService.getProblemsByCategory(category);
        } else if (status != null && status.equalsIgnoreCase("OPEN")){
            problems = problemService.getOpenProblems();
        } else {
            problems = problemService.getAllProblems();
        }
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/my-problems")
    public ResponseEntity<List<ProblemDTO>> getMyProblems(
            Authentication authentication
    ) {
        String ownerId = authentication.getName();
        List<ProblemDTO> problems = problemService.getProblemsByOwner(ownerId);
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDTO> getProblemsById(
            @PathVariable String id
    ) {
        ProblemDTO problem = problemService.getProblemById(id);
        return ResponseEntity.ok(problem);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProblemDTO> updateProblemStatus(
            @PathVariable String id,
            @RequestParam ProblemStatus status
    ) {
        ProblemDTO problem = problemService.updateProblemStatus(id, status);
        return ResponseEntity.ok(problem);
    }

}
