package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.task.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<String> handleTaskNotFound(TaskNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(TaskPayloadInvalid.class)
    public ResponseEntity<String> handleTaskPayloadInvalid(TaskPayloadInvalid e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(TaskNameTooLong.class)
    public ResponseEntity<String> handleTaskNameTooLong(TaskNameTooLong e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(TaskDescriptionTooLong.class)
    public ResponseEntity<String> handleTaskDescriptionTooLong(TaskDescriptionTooLong e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(TaskDueDateTooLong.class)
    public ResponseEntity<String> handleTaskDueDateTooLong(TaskDueDateTooLong e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }


}
