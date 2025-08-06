package com.ynov.sprintify.exceptions.sprint;

public class SprintDescriptionTooLong extends RuntimeException {

    public SprintDescriptionTooLong() {
        super("Sprint description is too long");
    }
}
