package com.ynov.sprintify.controllers.sprint;

import com.ynov.sprintify.controllers.SprintController;
import com.ynov.sprintify.dto.sprint.SprintOverviewDTO;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.payloads.SprintCreationPayload;
import com.ynov.sprintify.repositories.SprintRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles("test")
class GetSprintTest {

    @Autowired
    private SprintController sprintController;

    @Autowired
    private SprintRepository sprintRepository;

    @DisplayName("Lorsque je veux recuperer les sprints d'un projet existant, je dois pouvoir les recuperer")
    @Test
    void testGetSprintWithAValidProjectName() {
        ResponseEntity<?> responseEntity = sprintController.getSprintsForAProject("Projet Test");
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        assertNotNull(responseEntity.getBody());

        List<SprintOverviewDTO> sprints = (List<SprintOverviewDTO>) responseEntity.getBody();

        assertNotNull(sprints);
        assertFalse(sprints.isEmpty());
        assertEquals("Sprint 1", sprints.getFirst().name());
    }

    @DisplayName("Lorsque je veux recuperer les sprints d'un projet existant mais sans sprints, je recupere une liste vide")
    @Test
    @Transactional
    void testGetSprintWithAValidProjectNameWithoutSprints() {

        sprintRepository.deleteAll();

        ResponseEntity<?> responseEntity = sprintController.getSprintsForAProject("Projet Test");
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        assertNotNull(responseEntity.getBody());

        List<SprintOverviewDTO> sprints = (List<SprintOverviewDTO>) responseEntity.getBody();
        assertTrue(sprints.isEmpty());
    }

    @DisplayName("Lorsque je veux recuperer les sprints d'un projet qui n'existe pas, je reçois une erreur")
    @Test
    void testGetSprintWithAInvalidProjectName() {
        assertThrows(ProjectNotFound.class, () -> sprintController.getSprintsForAProject("unknown"));
    }

    }
