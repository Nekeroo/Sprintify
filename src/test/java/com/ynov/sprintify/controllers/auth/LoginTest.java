package com.ynov.sprintify.controllers.auth;

import com.ynov.sprintify.controllers.AuthController;
import com.ynov.sprintify.payloads.JwtResponse;
import com.ynov.sprintify.payloads.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class LoginTest {

    @Autowired
    private AuthController controller;

    @DisplayName("Lorsque je tente de me connecter avec des informations valides, ALORS je reçois un token et je me connecte\n")
    @Test
    void testLoginWithValidInformations() {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("demo")
                .password("pass1234")
                .build();

        ResponseEntity<?> response = controller.authenticateUser(loginRequest);

        assertTrue(response.getStatusCode().is2xxSuccessful());

        JwtResponse jwtResponse = (JwtResponse) response.getBody();

        assertNotNull(jwtResponse);

    }

    @DisplayName("Lorsque je tente de me connecter avec des informations invalides, ALORS je reçois une erreur\n")
    @Test
    void testLoginWithInvalidInformations() {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("unknow")
                .password("invalidPassword")
                .build();

        assertThrows(InternalAuthenticationServiceException.class, () -> controller.authenticateUser(loginRequest));

    }

}
