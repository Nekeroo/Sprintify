package com.ynov.sprintify.exceptions.project;

public class ProjectDescriptionTooLong extends RuntimeException {

    public ProjectDescriptionTooLong() {
        super("Project description too long");
    }
}
