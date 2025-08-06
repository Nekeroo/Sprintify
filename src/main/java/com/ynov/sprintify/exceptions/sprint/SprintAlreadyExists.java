package com.ynov.sprintify.exceptions.sprint;

public class SprintAlreadyExists extends RuntimeException {

    public SprintAlreadyExists() {
        super("Sprint already exists");

    }
}
