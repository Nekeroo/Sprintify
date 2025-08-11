package com.ynov.sprintify.exceptions.users;

public class UserUsernameTaken extends RuntimeException {
    public UserUsernameTaken() {
        super("Username already taken");
    }
}
