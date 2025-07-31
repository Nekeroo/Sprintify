package com.ynov.sprintify.exceptions.users;

public class UsernameTaken extends RuntimeException {
    public UsernameTaken() {
        super("Username already taken");
    }
}
