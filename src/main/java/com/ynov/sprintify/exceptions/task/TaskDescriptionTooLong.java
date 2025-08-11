package com.ynov.sprintify.exceptions.task;

public class TaskDescriptionTooLong extends RuntimeException {
    public TaskDescriptionTooLong() {
        super("Task description too long");
    }
}
