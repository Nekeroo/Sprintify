package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.project.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(ProjectNotFound.class)
    public ResponseEntity<String> handleProjectNotFound(ProjectNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectNameTooLong.class)
    public ResponseEntity<String> handleProjectNameTooLong(ProjectNameTooLong ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectOwnerNameTooLong.class)
    public ResponseEntity<String> handleProjectOwnerNameTooLong(ProjectOwnerNameTooLong ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectPayloadInvalid.class)
    public ResponseEntity<String> handleProjectPayloadInvalid(ProjectPayloadInvalid ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectDescriptionTooLong.class)
    public ResponseEntity<String> handleProjectDescriptionTooLong(ProjectDescriptionTooLong ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ProjectAlreadyExists.class)
    public ResponseEntity<String> handleProjectAlreadyExists(ProjectAlreadyExists ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
