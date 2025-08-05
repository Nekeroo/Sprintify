package com.ynov.sprintify.exceptions.project;

public class ProjectPayloadInvalid extends RuntimeException {

    public ProjectPayloadInvalid() {
        super("Invalid project data");
    }
}
