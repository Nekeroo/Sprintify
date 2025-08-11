package com.ynov.sprintify.exceptions.task;

public class TaskNameTooLong extends RuntimeException {
    public TaskNameTooLong() {
        super("Task name too long");
    }
}
