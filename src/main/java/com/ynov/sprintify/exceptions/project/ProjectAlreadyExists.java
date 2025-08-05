package com.ynov.sprintify.exceptions.project;

public class ProjectAlreadyExists extends RuntimeException {
    public ProjectAlreadyExists() {
        super("Project already exists");
    }
}
