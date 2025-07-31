package com.ynov.sprintify.controllers;

import com.ynov.sprintify.config.jwt.JwtTokenProvider;
import com.ynov.sprintify.models.Role;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.payloads.LoginRequest;
import com.ynov.sprintify.payloads.LoginResponse;
import com.ynov.sprintify.payloads.RegisterRequest;
import com.ynov.sprintify.services.RoleService;
import com.ynov.sprintify.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

    private final JwtTokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = userService.getUserByUsername(loginRequest.getUsername());
        String token = tokenProvider.generateToken(user.getUsername(),
                user.getRoles().stream().map(Role::getName).toList());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        userService.getUserByUsername(registerRequest.getUsername());

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        Role userRole = roleService.getRoleByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole));

        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}