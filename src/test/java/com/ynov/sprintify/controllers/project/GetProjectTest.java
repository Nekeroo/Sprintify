package com.ynov.sprintify.controllers.project;

import com.ynov.sprintify.controllers.ProjectController;
import com.ynov.sprintify.dto.project.ProjectDetailsDTO;
import com.ynov.sprintify.dto.project.ProjectOverviewDTO;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class GetProjectTest {

    @Autowired
    private ProjectController controller;

    @Autowired
    private ProjectRepository projectRepository;


    @DisplayName("Etant donné un nom de projet existant, alors j'obtiens le projet correspondant")
    @Test
    void testGetAProjectWithAValidName() {

        ResponseEntity<?> responseEntity = controller.getProjectByName("Projet Test");

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        ProjectDetailsDTO project = (ProjectDetailsDTO) responseEntity.getBody();

        assertNotNull(project);
        assertEquals("Projet Test", project.name());
    }

    @DisplayName("Etant donné un nom de projet inexistant, alors j'obtiens une erreur")
    @Test
    void testGetAProjectWithAInvalidName() {

        assertThrows(ProjectNotFound.class, () -> controller.getProjectByName("unknown"));

    }

    @DisplayName("Etant donne que je souhaite recuperer la liste de tous les projets, alors je recupere la liste de tous les projets")
    @Test
    void testGetAllProjectsWithProjectsInDatabase() {
        ResponseEntity<?> responseEntity = controller.getAllProjects();
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        List<ProjectOverviewDTO> projects = (List<ProjectOverviewDTO>) responseEntity.getBody();

        assertNotNull(projects);
        assertEquals(1, projects.size());
    }

    @DisplayName("Etant donne que je souhaite recuperer la liste de tous les projets et qu'il n'y en a pas, alors je recupere une liste vide")
    @Test
    @Transactional
    void testGetAllProjectsWithNoProjectsInDatabase() {
        projectRepository.deleteAll();

        ResponseEntity<?> responseEntity = controller.getAllProjects();
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        List<ProjectOverviewDTO> projects = (List<ProjectOverviewDTO>) responseEntity.getBody();

        assertNotNull(projects);
        assertEquals(0, projects.size());
    }


}
