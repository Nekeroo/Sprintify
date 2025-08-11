package com.ynov.sprintify.utils;

import com.ynov.sprintify.exceptions.users.UserEmailTooLong;
import com.ynov.sprintify.exceptions.users.UserPayloadInvalid;
import com.ynov.sprintify.exceptions.users.UserUsernameTooLong;
import com.ynov.sprintify.payloads.RegisterRequest;

public class UserValidator {

    private UserValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateUser(RegisterRequest userPayload) {
        if (userPayload.username().isEmpty() || userPayload.password().isEmpty() || userPayload.email().isEmpty()) {
            throw new UserPayloadInvalid();
        }

        if (userPayload.username().length() > 50) {
            throw new UserUsernameTooLong();
        }

        if (userPayload.email().length() > 50) {
            throw new UserEmailTooLong();
        }

    }

}
