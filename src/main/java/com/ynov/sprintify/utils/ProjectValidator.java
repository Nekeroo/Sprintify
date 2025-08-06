package com.ynov.sprintify.utils;

import com.ynov.sprintify.exceptions.project.ProjectDescriptionTooLong;
import com.ynov.sprintify.exceptions.project.ProjectNameTooLong;
import com.ynov.sprintify.exceptions.project.ProjectPayloadInvalid;
import com.ynov.sprintify.payloads.ProjectCreationPayload;

public class ProjectValidator {

    private ProjectValidator() {
        throw new IllegalStateException("Utility class");
    }


    public static void validateProject(ProjectCreationPayload projectPayload) {

        if (projectPayload.name().isEmpty() || projectPayload.description().isEmpty() || projectPayload.owner() == null) {
            throw new ProjectPayloadInvalid();
        }

        if (projectPayload.name().length() > 50) {
            throw new ProjectNameTooLong();
        }

        if (projectPayload.description().length() > 500) {
            throw new ProjectDescriptionTooLong();
        }
    }

}
