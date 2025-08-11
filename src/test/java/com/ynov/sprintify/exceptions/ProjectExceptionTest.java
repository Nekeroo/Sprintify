package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.project.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProjectExceptionTest {

    @DisplayName("Lorsqu'une ProjectNotFound est lancée, alors le message est correct")
    @Test
    void testProjectNotFound() {
        ProjectNotFound projectNotFound = new ProjectNotFound();
        assertEquals("Project not found", projectNotFound.getMessage());
    }

    @DisplayName("Lorsqu'une ProjectPayloadInvalid est lancée, alors le message est correct")
    @Test
    void testProjectPayloadInvalid() {
        ProjectPayloadInvalid projectPayloadInvalid = new ProjectPayloadInvalid();
        assertEquals("Invalid project data", projectPayloadInvalid.getMessage());
    }

    @DisplayName("Lorsqu'une ProjectDescriptionTooLong est lancée, alors le message est correct")
    @Test
    void testProjectDescriptionTooLong() {
        ProjectDescriptionTooLong projectDescriptionTooLong = new ProjectDescriptionTooLong();
        assertEquals("Project description too long", projectDescriptionTooLong.getMessage());
    }

    @DisplayName("Lorsqu'une ProjectNameTooLong est lancée, alors le message est correct")
    @Test
    void testProjectNameTooLong() {
        ProjectNameTooLong projectNameTooLong = new ProjectNameTooLong();
        assertEquals("Project name too long", projectNameTooLong.getMessage());
    }

    @DisplayName("Lorsqu'une ProjectOwnerNameTooLong est lancée, alors le message est correct")
    @Test
    void testProjectOwnerNameTooLong() {
        ProjectOwnerNameTooLong projectOwnerNameTooLong = new ProjectOwnerNameTooLong();
        assertEquals("Project owner name too long", projectOwnerNameTooLong.getMessage());
    }

    @DisplayName("Lorsqu'une ProjectAlreadyExists est lancée, alors le message est correct")
    @Test
    void testProjectAlreadyExists() {
        ProjectAlreadyExists projectAlreadyExists = new ProjectAlreadyExists();
        assertEquals("Project already exists", projectAlreadyExists.getMessage());
    }
}
