package com.ynov.sprintify.exceptions.project;

public class ProjectNameTooLong extends RuntimeException {
    public ProjectNameTooLong() {
        super("Project name too long");
    }
}
