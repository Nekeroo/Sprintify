package com.ynov.sprintify.exceptions.handlers;

import com.ynov.sprintify.exceptions.TaskExceptionHandler;
import com.ynov.sprintify.exceptions.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskExceptionHandlerTest {

    private TaskExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new TaskExceptionHandler();
    }

    @Test
    void handleTaskNotFound_returns404() {
        TaskNotFound ex = new TaskNotFound();
        ResponseEntity<String> response = handler.handleTaskNotFound(ex);
        assertEquals(404, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleTaskPayloadInvalid_returns400() {
        TaskPayloadInvalid ex = new TaskPayloadInvalid();
        ResponseEntity<String> response = handler.handleTaskPayloadInvalid(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleTaskNameTooLong_returns400() {
        TaskNameTooLong ex = new TaskNameTooLong();
        ResponseEntity<String> response = handler.handleTaskNameTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleTaskDescriptionTooLong_returns400() {
        TaskDescriptionTooLong ex = new TaskDescriptionTooLong();
        ResponseEntity<String> response = handler.handleTaskDescriptionTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }

    @Test
    void handleTaskDueDateTooLong_returns400() {
        TaskDueDateTooLong ex = new TaskDueDateTooLong();
        ResponseEntity<String> response = handler.handleTaskDueDateTooLong(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals(ex.getMessage(), response.getBody());
    }
}