package com.ynov.sprintify.exceptions.handlers;

import com.ynov.sprintify.exceptions.GlobalExceptionHandler;
import com.ynov.sprintify.exceptions.roles.RoleNotFound;
import com.ynov.sprintify.exceptions.users.Unauthorized;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleRoleNotFound_returns404() {
        RoleNotFound ex = new RoleNotFound();
        ResponseEntity<String> response = handler.handleRoleNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleUnauthorized_returns401() {
        Unauthorized ex = new Unauthorized();
        ResponseEntity<String> response = handler.handleUnauthorized(ex);
        assertEquals(401, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }
}