package com.ynov.sprintify.controllers.task;

import com.ynov.sprintify.controllers.TaskController;
import com.ynov.sprintify.exceptions.task.TaskNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DeleteTaskTest {

    @Autowired
    private TaskController taskController;

    @DisplayName("Lorsque je tente de supprimer une tâche non existant, alors je reçois une erreur")
    @Test
    void testDeleteTaskWithAInvalidTaskName() {
        assertThrows(TaskNotFound.class, () -> taskController.deleteTask("Tache 12"));
    }

    @DisplayName("Lorsque je tente de supprimer une tâche existant, alors je reçois une erreur")
    @Test
    void testDeleteTaskWithAValidTaskName() {

        ResponseEntity<?> responseEntity = taskController.deleteTask("Tache 1");

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

}
