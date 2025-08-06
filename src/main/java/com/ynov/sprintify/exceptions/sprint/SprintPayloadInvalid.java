package com.ynov.sprintify.exceptions.sprint;

public class SprintPayloadInvalid extends RuntimeException {

    public SprintPayloadInvalid() {
        super("Sprint payload is invalid");
    }
}
