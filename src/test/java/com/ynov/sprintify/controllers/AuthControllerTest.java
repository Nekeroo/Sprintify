package com.ynov.sprintify.controllers;

import com.ynov.sprintify.payloads.RegisterRequest;
import com.ynov.sprintify.services.UserService;
import com.ynov.sprintify.config.jwt.JwtTokenProvider;
import com.ynov.sprintify.models.Role;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.repositories.UserRepository;
import com.ynov.sprintify.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AuthControllerTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected BCryptPasswordEncoder passwordEncoder;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    protected Role role;

    protected User user;

    protected AuthController controller;

    protected RegisterRequest registerDTO;

    @BeforeEach
    void setUp() {
        role = Role.builder().id(1L).name("USER").build();

        user = User.builder()
                .id(1L)
                .username("John Doe")
                .email("johndoe@gmail.com")
                .role(role)
                .build();

        RegisterRequest registerRequest = RegisterRequest.builder().password("toto").username("Dupont Dupont").email("dupont@gmail.com").build();

        controller = new AuthController(authenticationManager, userService, roleService, jwtTokenProvider, passwordEncoder);
    }

}
