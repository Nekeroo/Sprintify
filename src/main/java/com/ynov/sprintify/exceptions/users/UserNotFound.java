package com.ynov.sprintify.exceptions.users;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
}

