package com.ynov.sprintify.exceptions.project;

public class ProjectNotFound extends RuntimeException {
    public ProjectNotFound() {
        super("Project not found");
    }
}
