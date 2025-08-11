package com.ynov.sprintify.controllers.task;

import com.ynov.sprintify.controllers.TaskController;
import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
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
class GetTaskTest {

    @Autowired
    private TaskController controller;

    @DisplayName("Lorsque je tente de récupérer des tâches sur un sprint existant, alors je reçois la liste des tâches")
    @Test
    void testGettaskWithAValidProjectName() {
        ResponseEntity<?> responseEntity = controller.getTaskBySprintName("Sprint 1");

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        List<TaskDetailDTO> tasks = (List<TaskDetailDTO>) responseEntity.getBody();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals("Tache 1", tasks.get(0).title());
    }

    @DisplayName("Lorsque je tente de récupérer des tâches sur un sprint non existant, alors je reçois une erreur")
    @Test
    void testGettaskWithAInvalidProjectName() {

        assertThrows(SprintNotFound.class, () -> controller.getTaskBySprintName("Sprint"));
    }


}
