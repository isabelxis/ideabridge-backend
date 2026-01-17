package com.ideabridge_backend.ideabridge_backend.repository;

import com.ideabridge_backend.ideabridge_backend.model.Problem;
import com.ideabridge_backend.ideabridge_backend.model.ProblemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemsRepository extends JpaRepository<Problem, String> {

    List<Problem> findByOwnerId(String ownerId);
    List<Problem> findByStatus(ProblemStatus status);
    List<Problem> findByCategory(String category);

    @Query("SELECT p FROM Problem p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Problem> searchByTitleOrDescription(@Param("query") String query);

    @Query("SELECT p FROM Problem p WHERE p.status = 'OPEN' ORDER BY p.createdAt DESC")
    List<Problem> findRecentOpenProblems();

}
