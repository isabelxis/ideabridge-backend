package com.ideabridge_backend.ideabridge_backend.repository;

import com.ideabridge_backend.ideabridge_backend.model.User;
import com.ideabridge_backend.ideabridge_backend.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByRole(UserRole role);
}
