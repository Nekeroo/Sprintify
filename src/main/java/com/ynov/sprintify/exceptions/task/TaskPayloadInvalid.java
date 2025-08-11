package com.ynov.sprintify.exceptions.task;

public class TaskPayloadInvalid extends RuntimeException {
    public TaskPayloadInvalid() {
        super("Task payload invalid");
    }
}
