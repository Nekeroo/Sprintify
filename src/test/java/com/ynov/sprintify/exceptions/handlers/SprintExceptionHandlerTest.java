package com.ynov.sprintify.exceptions.handlers;

import com.ynov.sprintify.exceptions.SprintExceptionHandler;
import com.ynov.sprintify.exceptions.sprint.SprintDescriptionTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNameTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.exceptions.sprint.SprintPayloadInvalid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SprintExceptionHandlerTest {

    private SprintExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SprintExceptionHandler();
    }

    @Test
    void handleSprintNotFound_returns404() {
        SprintNotFound ex = new SprintNotFound();
        ResponseEntity<String> response = handler.handleSprintNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleSprintPayloadInvalid_returns400() {
        SprintPayloadInvalid ex = new SprintPayloadInvalid();
        ResponseEntity<String> response = handler.handleSprintPayloadInvalid(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleSprintNameTooLong_returns400() {
        SprintNameTooLong ex = new SprintNameTooLong();
        ResponseEntity<String> response = handler.handleSprintNameTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleSprintDescriptionTooLong_returns400() {
        SprintDescriptionTooLong ex = new SprintDescriptionTooLong();
        ResponseEntity<String> response = handler.handleSprintDescriptionTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }
}