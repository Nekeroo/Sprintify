package com.ynov.sprintify.controllers.project;

import com.ynov.sprintify.controllers.ProjectController;
import com.ynov.sprintify.dto.ProjectDTO;
import com.ynov.sprintify.exceptions.project.ProjectAlreadyExists;
import com.ynov.sprintify.exceptions.project.ProjectDescriptionTooLong;
import com.ynov.sprintify.exceptions.project.ProjectNameTooLong;
import com.ynov.sprintify.exceptions.project.ProjectPayloadInvalid;
import com.ynov.sprintify.exceptions.users.UserNotFound;
import com.ynov.sprintify.payloads.ProjectCreationPayload;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class CreationProjectTest {

    @Autowired
    private ProjectController controller;



    @DisplayName("Etant donné que je fournis un titre valid et un description valide, alors j'arrive a créer un projet")
    @Test
    void testCreateProjectWithValidTitleAndValidDescription() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("Projet Test Integration")
                .description("Test integration")
                .owner("demo")
                .build();

        ResponseEntity<?> responseEntity = controller.createProject(inputDTO);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        ProjectDTO project = (ProjectDTO) responseEntity.getBody();

        assertNotNull(project);
        assertEquals(inputDTO.name(), project.getName());
        assertEquals(inputDTO.description(), project.getDescription());
        assertEquals(inputDTO.owner(), project.getOwner().getUsername());
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre valide et une description vide, alors je reçois une erreur")
    @Test
    void testCreateProjectWithValidTitleAndInvalidDescription() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("Projet Test Integration")
                .description("")
                .owner("demo")
                .build();

        assertThrows(ProjectPayloadInvalid.class, () -> controller.createProject(inputDTO));
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre valide et une description trop longue, alors je reçois une erreur")
    @Test
    void testCreateProjectWithValidTitleAndInvalidDescriptionTooLong() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("Projet Test Integration")
                .description("e".repeat(501))
                .owner("demo")
                .build();

        assertThrows(ProjectDescriptionTooLong.class, () -> controller.createProject(inputDTO));
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre vide et une description valide, alors je reçois une erreur")
    @Test
    void testCreateProjectWithInvalidTitleAndValidDescription() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("")
                .description("Test integration")
                .owner("demo")
                .build();

        assertThrows(ProjectPayloadInvalid.class, () -> controller.createProject(inputDTO));
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre vide et une description valide, alors je reçois une erreur")
    @Test
    void testCreateProjectWithTitleTooLongAndValidDescription() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("e".repeat(51))
                .description("Test integration")
                .owner("demo")
                .build();

        assertThrows(ProjectNameTooLong.class, () -> controller.createProject(inputDTO));
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre existant et une description valide, alors je reçois une erreur")
    @Test
    void testCreateProjectWithTExistingTitleAndValidDescription() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("Projet Test")
                .description("Test integration")
                .owner("demo")
                .build();

        assertThrows(ProjectAlreadyExists.class, () -> controller.createProject(inputDTO));
    }

    @DisplayName("ÉTANT DONNÉ QUE je fournis un titre valide,  une description valide et un utilisateur non existant, alors je reçois une erreur")
    @Test
    void testCreateProjectWithValidTitleAndValidDescriptionAndInvalidUser() {

        ProjectCreationPayload inputDTO = ProjectCreationPayload.builder()
                .name("Projet Test Integration")
                .description("Test integration")
                .owner("inconnu")
                .build();

        assertThrows(UserNotFound.class, () -> controller.createProject(inputDTO));
    }
}
