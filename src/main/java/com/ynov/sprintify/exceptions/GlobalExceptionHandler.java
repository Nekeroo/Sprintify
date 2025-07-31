package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.roles.RoleNotFound;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.exceptions.users.UserNotFound;
import com.ynov.sprintify.exceptions.users.UsernameTaken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<String> handleRoleNotFound(RoleNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameTaken.class)
    public ResponseEntity<String> handleUsernameTaken(UsernameTaken ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(SprintNotFound.class)
    public ResponseEntity<String> handleSprintNotFound(SprintNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}
