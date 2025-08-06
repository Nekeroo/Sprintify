package com.ynov.sprintify.controllers.sprint;

import com.ynov.sprintify.controllers.SprintController;
import com.ynov.sprintify.dto.SprintDTO;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.exceptions.sprint.SprintDescriptionTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintNameTooLong;
import com.ynov.sprintify.exceptions.sprint.SprintPayloadInvalid;
import com.ynov.sprintify.payloads.SprintCreationPayload;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.ynov.sprintify.exceptions.sprint.SprintAlreadyExists;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class CreateSprint {

    @Autowired
    private SprintController sprintController;

    @DisplayName("Etant donné un sprint existant avec le nom donné, alors je reçois une erreur")
    @Test
    void testCreateSprintWithANameThatAlreadyExists() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("Sprint 1")
                .description("Description")
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

        assertThrows(SprintAlreadyExists.class, () -> sprintController.addSprintToProject("Projet Test", sprintPayload));
    }

    @DisplayName("Etant donné un payload valide et un nom de projet non existant, alors je reçois une erreur")
    @Test
    void testCreateSprintWithAValidPayloadButInvalidProject() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("Sprint 2")
                .description("Description")
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

        assertThrows(ProjectNotFound.class, () -> sprintController.addSprintToProject("unknown", sprintPayload));
    }

    @DisplayName("Etant donné un payload avec un nom de sprint trop long, alors je reçois une erreur")
    @Test
    void testCreateSprintWithInvalidName() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("e".repeat(51))
                .description("Description")
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

        assertThrows(SprintNameTooLong.class, () -> sprintController.addSprintToProject("Projet Test", sprintPayload));
    }

    @DisplayName("Etant donné un payload avec une descriptions de sprint trop longue, alors je reçois une erreur")
    @Test
    void testCreateSprintWithInvalidDescription() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("Sprint 2")
                .description("e".repeat(501))
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

        assertThrows(SprintDescriptionTooLong.class, () -> sprintController.addSprintToProject("Projet Test", sprintPayload));
    }

    @DisplayName("Etant donné un payload avec un nom vide, alors je reçois une erreur")
    @Test
    void testCreateSprintWithEmptyName() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("")
                .description("Description")
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

        assertThrows(SprintPayloadInvalid.class, () -> sprintController.addSprintToProject("Projet Test", sprintPayload));
    }

    @DisplayName("Etant donné un payload avec un payload valide et un projet existant, alors je crée et reçois le projet")
    @Test
    void testCreateSprintWithAValidPayload() {
        SprintCreationPayload sprintPayload = SprintCreationPayload.builder()
                .name("Sprint 2")
                .description("Description")
                .startDate("2023-01-01")
                .endDate("2023-01-31")
                .build();

       ResponseEntity<?> response = sprintController.addSprintToProject("Projet Test", sprintPayload);

       assertTrue(response.getStatusCode().is2xxSuccessful());

       SprintDTO sprint = (SprintDTO) response.getBody();

       assertNotNull(sprint);
       assertEquals(sprintPayload.name(), sprint.getName());
       assertEquals(sprintPayload.startDate(), sprint.getStartDate());
       assertEquals(sprintPayload.endDate(), sprint.getEndDate());
    }

}
