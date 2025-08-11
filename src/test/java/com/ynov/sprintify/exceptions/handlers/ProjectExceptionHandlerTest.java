package com.ynov.sprintify.exceptions.handlers;

import com.ynov.sprintify.exceptions.ProjectExceptionHandler;
import com.ynov.sprintify.exceptions.project.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectExceptionHandlerTest {

    private ProjectExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ProjectExceptionHandler();
    }

    @Test
    void handleProjectNotFound_returns404() {
        ProjectNotFound ex = new ProjectNotFound();
        ResponseEntity<String> response = handler.handleProjectNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleProjectNameTooLong_returns400() {
        ProjectNameTooLong ex = new ProjectNameTooLong();
        ResponseEntity<String> response = handler.handleProjectNameTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleProjectOwnerNameTooLong_returns400() {
        ProjectOwnerNameTooLong ex = new ProjectOwnerNameTooLong();
        ResponseEntity<String> response = handler.handleProjectOwnerNameTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleProjectPayloadInvalid_returns400() {
        ProjectPayloadInvalid ex = new ProjectPayloadInvalid();
        ResponseEntity<String> response = handler.handleProjectPayloadInvalid(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleProjectDescriptionTooLong_returns400() {
        ProjectDescriptionTooLong ex = new ProjectDescriptionTooLong();
        ResponseEntity<String> response = handler.handleProjectDescriptionTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleProjectAlreadyExists_returns400() {
        ProjectAlreadyExists ex = new ProjectAlreadyExists();
        ResponseEntity<String> response = handler.handleProjectAlreadyExists(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }
}