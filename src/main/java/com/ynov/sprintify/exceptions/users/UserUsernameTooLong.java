package com.ynov.sprintify.exceptions.users;

public class UserUsernameTooLong extends RuntimeException {
    public UserUsernameTooLong() {
        super("User username too long");
    }
}
