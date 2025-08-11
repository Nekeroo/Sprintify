package com.ynov.sprintify.controllers.auth;

import com.ynov.sprintify.controllers.AuthController;
import com.ynov.sprintify.exceptions.users.UserEmailTooLong;
import com.ynov.sprintify.exceptions.users.UserPayloadInvalid;
import com.ynov.sprintify.exceptions.users.UserUsernameTaken;
import com.ynov.sprintify.exceptions.users.UserUsernameTooLong;
import com.ynov.sprintify.payloads.JwtResponse;
import com.ynov.sprintify.payloads.RegisterRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RegisterTest {

    @Autowired
    private AuthController controller;

    @DisplayName("Lorsque je fournis des informations valides, ALORS je reçois un token\n")
    @Test
    void testRegisterOneUserWithValidInformations() {

        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("testUser")
                .password("validPassword123")
                .email("H9F0A@example.com")
                .build();

        ResponseEntity<?> response = controller.registerUser(registerRequest);

        assertTrue(response.getStatusCode().is2xxSuccessful());

        JwtResponse jwtResponse = (JwtResponse) response.getBody();

        assertNotNull(jwtResponse);
    }

    @DisplayName("Lorsque je fournis un username deja pris, ALORS je reçois une erreur\n")
    @Test
    void testRegisterOneUserWithEmailAlreadyTaken() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("demo")
                .password("validPassword123")
                .email("H9F0A@example.com")
                .build();

        assertThrows(UserUsernameTaken.class, () -> controller.registerUser(registerRequest));
    }

    @DisplayName("Lorsque je fournis un email invalide, ALORS je reçois une erreur\n")
    @Test
    void testRegisterOneUserWithInvalidEmailFormat() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("testUser")
                .password("e")
                .email("e".repeat(51))
                .build();

        assertThrows(UserEmailTooLong.class, () -> controller.registerUser(registerRequest));
    }

    @DisplayName("Lorsque je fournis un username invalide, ALORS je reçois une erreur\n")
    @Test
    void testRegisterOneUserWithInvalidName() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("e".repeat(51))
                .password("e")
                .email("H9F0A@example.com")
                .build();


        assertThrows(UserUsernameTooLong.class, () -> controller.registerUser(registerRequest));
    }

    @DisplayName("Lorsque je fournis un username invalide, ALORS je reçois une erreur\n")
    @Test
    void testRegisterOneUserWithNameTooLong() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("")
                .password("validPassword123")
                .email("H9F0A@example.com")
                .build();

        assertThrows(UserPayloadInvalid.class, () -> controller.registerUser(registerRequest));
    }

}
