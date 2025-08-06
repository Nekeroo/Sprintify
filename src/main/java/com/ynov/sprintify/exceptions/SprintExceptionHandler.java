package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.sprint.SprintDescriptionTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNameTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.exceptions.sprint.SprintPayloadInvalid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SprintExceptionHandler {

    @ExceptionHandler(SprintNotFound.class)
    public ResponseEntity<String> handleSprintNotFound(SprintNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(SprintPayloadInvalid.class)
    public ResponseEntity<String> handleSprintPayloadInvalid(SprintPayloadInvalid ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(SprintNameTooLong.class)
    public ResponseEntity<String> handleSprintNameTooLong(SprintNameTooLong ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(SprintDescriptionTooLong.class)
    public ResponseEntity<String> handleSprintDescriptionTooLong(SprintDescriptionTooLong ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
