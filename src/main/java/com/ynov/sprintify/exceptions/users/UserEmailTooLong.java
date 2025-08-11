package com.ynov.sprintify.exceptions.users;

public class UserEmailTooLong extends RuntimeException {
    public UserEmailTooLong() {
        super("User email too long");
    }
}
