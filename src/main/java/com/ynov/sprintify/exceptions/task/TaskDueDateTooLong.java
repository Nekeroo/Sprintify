package com.ynov.sprintify.exceptions.task;

public class TaskDueDateTooLong extends RuntimeException {
    public TaskDueDateTooLong() {
        super("Task due date too long");
    }
}
