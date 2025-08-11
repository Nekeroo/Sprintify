package com.ynov.sprintify.exceptions.task;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound() {
        super("Task not found");
    }

}
