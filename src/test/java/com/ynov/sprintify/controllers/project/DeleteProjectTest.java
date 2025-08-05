package com.ynov.sprintify.controllers.project;


import com.ynov.sprintify.controllers.ProjectController;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class DeleteProjectTest {

    @Autowired
    private ProjectController projectController;

    @DisplayName("Etant donné un nom de projet existant, alors je peux le supprimer")
    @Test
    void testDeleteAProjectWithAValidName() {

        ResponseEntity<?> responseEntity = projectController.deleteProject("Projet Test");

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @DisplayName("Etant donné un nom de projet non existant, alors je reçois une erreur")
    @Test
    void testDeleteAProjectWithAInvalidName() {
        assertThrows(ProjectNotFound.class, () -> projectController.deleteProject("unknown"));
    }

}
