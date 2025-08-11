package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.users.Unauthorized;
import com.ynov.sprintify.exceptions.users.UserNotFound;
import com.ynov.sprintify.exceptions.users.UserUsernameTaken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class UserExceptionTest {

    @DisplayName("Lorsqu'une UserNotFound est lancée, alors le message est correct")
    @Test
    void testUserNotFound() {
        UserNotFound userNotFound = new UserNotFound();
        assertEquals("User not found", userNotFound.getMessage());
    }

    @DisplayName("Lorsqu'une UsernameTaken est lancée, alors le message est correct")
    @Test
    void testUsernameTaken() {
        UserUsernameTaken userUsernameTaken = new UserUsernameTaken();
        assertEquals("Username already taken", userUsernameTaken.getMessage());
    }

    @DisplayName("Lorsqu'une Unauthorized est lancée, alors le message est correct")
    @Test
    void testUnauthorized() {
        Unauthorized unauthorized = new Unauthorized();
        assertEquals("Unauthorized", unauthorized.getMessage());
    }

}
