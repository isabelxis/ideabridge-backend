package com.ideabridge_backend.ideabridge_backend.service;

import com.ideabridge_backend.ideabridge_backend.dto.ProblemCreateDTO;
import com.ideabridge_backend.ideabridge_backend.dto.ProblemDTO;
import com.ideabridge_backend.ideabridge_backend.model.Problem;
import com.ideabridge_backend.ideabridge_backend.model.ProblemStatus;
import com.ideabridge_backend.ideabridge_backend.repository.ProblemsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemService {
    // Business logic for managing problems will be implemented here
    private final ProblemsRepository problemsRepository;

    @Transactional
    public ProblemDTO createProblem(String ownerId, ProblemCreateDTO dto) {
        // Implementation for creating a new problem
        Problem problem = new Problem();
        problem.setOwnerId (ownerId);
        problem.setTitle(dto.getTitle());
        problem.setDescription(dto.getDescription());
        problem.setCategory(dto.getCategory());
        problem.setUrgency(dto.getUrgency());
        problem.setBudget(dto.getBudget());
        problem.setTimeline(dto.getTimeline());
        problem.setStatus(ProblemStatus.OPEN);

        Problem savedProblem =  problemsRepository.save(problem);

        return convertToDTO(savedProblem); // Placeholder return
    }

    @Transactional
    public ProblemDTO updateProblemStatus(String id, ProblemStatus status) {
        Problem problem = problemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        problem.setStatus(status);
        Problem updatedProblem = problemsRepository.save(problem);
        return convertToDTO(updatedProblem);
    }

    public ProblemDTO getProblemById(String id) {
        Problem problem = problemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        return convertToDTO(problem);
    }

    public List<ProblemDTO> getAllProblems() {
        return problemsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getProblemsByOwner(String ownerId) {
        return problemsRepository.findByOwnerId(ownerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getOpenProblems() {
        return problemsRepository.findByStatus(ProblemStatus.OPEN).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> searchProblems(String query) {
        return problemsRepository.searchByTitleOrDescription(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProblemDTO> getProblemsByCategory(String category) {
        return problemsRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProblemDTO convertToDTO(Problem problem) {
        // Conversion logic from Problem entity to ProblemDTO
        return new ProblemDTO(
                problem.getId(),
                problem.getOwnerId(),
                problem.getTitle(),
                problem.getDescription(),
                problem.getCategory(),
                problem.getUrgency(),
                problem.getStatus(),
                problem.getBudget(),
                problem.getTimeline(),
                problem.getAttachments(),
                problem.getCreatedAt(),
                problem.getUpdatedAt()

        ); // Placeholder return
    }


}
