package com.ynov.sprintify.exceptions.users;

public class UserPayloadInvalid extends RuntimeException {
    public UserPayloadInvalid() {
        super("User payload invalid");
    }
}
