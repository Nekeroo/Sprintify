package com.ynov.sprintify.controllers.sprint;

import com.ynov.sprintify.controllers.SprintController;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
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
class DeleteSprint {

    @Autowired
    private SprintController sprintController;

    @DisplayName("Lorsque je veux supprimer un sprint avec un nom inexistant, je dois obtenir une erreur")
    @Test
    void testDeleteSprintWithInvalidName() {
        assertThrows(SprintNotFound.class, () -> sprintController.deleteSprint("unknown"));
    }

    @DisplayName("Lorsque je veux supprimer un sprint avec un nom existant, je dois pouvoir le supprimer")
    @Test
    void testDeleteSprintWithValidName() {
        ResponseEntity<?> responseEntity = sprintController.deleteSprint("Sprint 1");
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

}
