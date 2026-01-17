package com.ideabridge_backend.ideabridge_backend.filter;

import com.ideabridge_backend.ideabridge_backend.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String username = jwtService.extractUsername(token);
                String userId = jwtService.extractClaim(token, claims -> claims.get("userId", String.class));
                String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtService.validateToken(token, username)) {
                        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_" + role));

                        Authentication autentication = new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                authorities
                        );
                        SecurityContextHolder.getContext().setAuthentication(autentication);
                    }
                }
            } catch (Exception e) {
                // Invalid token, proceed without setting authentication
            }
        }
        filterChain.doFilter(request, response);
    }
}
