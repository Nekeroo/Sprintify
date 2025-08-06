package com.ynov.sprintify.exceptions.sprint;

public class SprintNameTooLong extends RuntimeException {

    public SprintNameTooLong() {
        super("Sprint name is too long");
    }
}
