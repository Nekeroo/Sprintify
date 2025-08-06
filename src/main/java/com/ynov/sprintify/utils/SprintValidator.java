package com.ynov.sprintify.utils;

import com.ynov.sprintify.exceptions.sprint.SprintDescriptionTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNameTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintPayloadInvalid;
import com.ynov.sprintify.payloads.SprintCreationPayload;

public class SprintValidator {

    private SprintValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateSprint(SprintCreationPayload sprintPayload) {
        if (sprintPayload.name().isEmpty() || sprintPayload.description().isEmpty() || sprintPayload.startDate().isEmpty() || sprintPayload.endDate().isEmpty()) {
            throw new SprintPayloadInvalid();
        }

        if (sprintPayload.name().length() > 50) {
            throw new SprintNameTooLong();
        }

        if (sprintPayload.description().length() > 500) {
            throw new SprintDescriptionTooLong();
        }
    }
}
