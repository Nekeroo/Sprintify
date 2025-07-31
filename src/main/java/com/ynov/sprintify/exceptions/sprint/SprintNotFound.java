package com.ynov.sprintify.exceptions.sprint;

public class SprintNotFound extends RuntimeException {
    public SprintNotFound() {
        super("Sprint not found");
    }
}
