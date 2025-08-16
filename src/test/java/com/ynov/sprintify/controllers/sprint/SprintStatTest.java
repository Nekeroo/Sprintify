package com.ynov.sprintify.controllers.sprint;

import com.ynov.sprintify.controllers.SprintController;
import com.ynov.sprintify.dto.sprint.StatDTO;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SprintStatTest {

    @Autowired
    private SprintController sprintController;


    @DisplayName("Lorsque je tente de revoir les statistiques d’un sprint non existant, alors je reçois une erreur")
    @Test
    void getSprintStatWitnInvalidName() {

        assertThrows(SprintNotFound.class, () -> sprintController.getSprintStats("unknown"));
    }

    @DisplayName("Lorsque je tente de revoir les statistiques d’un sprint non existant, alors je reçois une erreur")
    @Test
    void getSprintStatWithValidName() {

        ResponseEntity<StatDTO> responseEntity = sprintController.getSprintStats("Sprint 1");
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        StatDTO stat = responseEntity.getBody();

        assertNotNull(stat);
        assertEquals("Sprint 1", stat.name());
    }
}
