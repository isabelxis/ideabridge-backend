package com.ideabridge_backend.ideabridge_backend.dto;

import com.ideabridge_backend.ideabridge_backend.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String company;
    private String bio;
    private UserRole role;
    private List<String> skills;
    private LocalDateTime createdAt;
}
