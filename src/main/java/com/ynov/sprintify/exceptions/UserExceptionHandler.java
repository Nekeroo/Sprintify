package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.users.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserUsernameTaken.class)
    public ResponseEntity<String> handleUsernameTaken(UserUsernameTaken e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(UserEmailTooLong.class)
    public ResponseEntity<String> handleUserEmailTooLong(UserEmailTooLong e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(UserUsernameTooLong.class)
    public ResponseEntity<String> handleUserUsernameTooLong(UserUsernameTooLong e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(UserPayloadInvalid.class)
    public ResponseEntity<String> handleUserPayloadInvalid(UserPayloadInvalid e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
