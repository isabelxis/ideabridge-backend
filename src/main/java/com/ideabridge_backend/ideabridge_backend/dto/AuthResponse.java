package com.ideabridge_backend.ideabridge_backend.dto;

import com.ideabridge_backend.ideabridge_backend.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String id;
    private String email;
    private String nome;
    private UserRole role;
}
