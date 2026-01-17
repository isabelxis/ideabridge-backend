package com.ideabridge_backend.ideabridge_backend.controller;

import com.ideabridge_backend.ideabridge_backend.dto.UserDTO;
import com.ideabridge_backend.ideabridge_backend.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String userId = authentication.getName();
        UserDTO user = userService.getCurrentUser(userId);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateUser(
            Authentication authentication,
            @RequestBody UserDTO userDTO
    ) {
        String userId = authentication.getName();
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }


}
