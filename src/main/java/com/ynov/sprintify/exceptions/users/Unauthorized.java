package com.ynov.sprintify.exceptions.users;

public class Unauthorized extends RuntimeException {
    public Unauthorized() {
        super("Unauthorized");
    }
}
