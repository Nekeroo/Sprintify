package com.ynov.sprintify.exceptions.handlers;

import com.ynov.sprintify.exceptions.UserExceptionHandler;
import com.ynov.sprintify.exceptions.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserExceptionHandlerTest {

    private UserExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new UserExceptionHandler();
    }

    @Test
    void handleUsernameTaken_returns400() {
        UserUsernameTaken ex = new UserUsernameTaken();
        ResponseEntity<String> response = handler.handleUsernameTaken(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleUserNotFound_returns404() {
        UserNotFound ex = new UserNotFound();
        ResponseEntity<String> response = handler.handleUserNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleUserEmailTooLong_returns400() {
        UserEmailTooLong ex = new UserEmailTooLong();
        ResponseEntity<String> response = handler.handleUserEmailTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleUserUsernameTooLong_returns400() {
        UserUsernameTooLong ex = new UserUsernameTooLong();
        ResponseEntity<String> response = handler.handleUserUsernameTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleUserPayloadInvalid_returns400() {
        UserPayloadInvalid ex = new UserPayloadInvalid();
        ResponseEntity<String> response = handler.handleUserPayloadInvalid(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }
}