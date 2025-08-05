package com.ynov.sprintify.exceptions.project;

public class ProjectOwnerNameTooLong extends RuntimeException {

    public ProjectOwnerNameTooLong() {
        super("Project owner name too long");
    }
}
