package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.roles.RoleNotFound;
import com.ynov.sprintify.exceptions.users.Unauthorized;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<String> handleRoleNotFound(RoleNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<String> handleUnauthorized(Unauthorized ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

}
