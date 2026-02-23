package com.employee.controller;

import com.employee.dto.AuthRequest;
import com.employee.dto.AuthResponse;
import com.employee.dto.LoginRequest;
import com.employee.dto.RefreshTokenRequest;
import com.employee.dto.StandardResponse;
import com.employee.service.AuthService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private static final Logger logger = LogManager.getLogger(AuthController.class);
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<StandardResponse<AuthResponse>> register(@Valid @RequestBody AuthRequest request) {
        logger.info("Registration request received for username: {}", request.getUsername());
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StandardResponse.success("User registered successfully", response));
    }
    
  
    
    @PostMapping("/login")
    public ResponseEntity<StandardResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Login request received for username: {}", request.getUsername());
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(StandardResponse.success("Login successful", response));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<StandardResponse<AuthResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        logger.info("Refresh token request received");
        AuthResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(StandardResponse.success("Token refreshed successfully", response));
    }
}
