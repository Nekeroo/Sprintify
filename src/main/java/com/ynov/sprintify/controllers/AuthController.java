package com.ynov.sprintify.controllers;

import com.ynov.sprintify.config.jwt.JwtTokenProvider;
import com.ynov.sprintify.exceptions.users.Unauthorized;
import com.ynov.sprintify.exceptions.users.UsernameTaken;
import com.ynov.sprintify.models.CustomUserDetails;
import com.ynov.sprintify.models.Role;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.payloads.JwtResponse;
import com.ynov.sprintify.payloads.LoginRequest;
import com.ynov.sprintify.payloads.RegisterRequest;
import com.ynov.sprintify.services.RoleService;
import com.ynov.sprintify.services.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        User user = userService.getUserByUsername(loginRequest.username());
        String token = tokenProvider.generateToken(user.getUsername(), List.of(user.getRole().getName()));

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userService.userExists(registerRequest.username())) {
            throw new UsernameTaken();
        }
        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());

        Role userRole = roleService.getRoleByName("ROLE_USER");
        user.setRole(userRole);

        userService.saveUser(user);

        String token = tokenProvider.generateToken(user.getUsername(), List.of(user.getRole().getName()));

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(@AuthenticationPrincipal CustomUserDetails userDetails, HttpServletRequest request) {
        if (userDetails == null) {
            throw new Unauthorized();
        }

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new Unauthorized();
            }

            User user = userService.getUser(userDetails.getUsername());

            return ResponseEntity.ok().body(user);
        } catch (JwtException e) {
            throw new Unauthorized();
        }
    }
}