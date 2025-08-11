package com.ynov.sprintify.controllers.task;

import com.ynov.sprintify.controllers.TaskController;
import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.exceptions.task.TaskDescriptionTooLong;
import com.ynov.sprintify.exceptions.task.TaskNameTooLong;
import com.ynov.sprintify.exceptions.task.TaskNotFound;
import com.ynov.sprintify.exceptions.task.TaskPayloadInvalid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UpdateTaskTest {

    @Autowired
    private TaskController taskController;

    @DisplayName("Lorsque je tente de mettre à jour une tâche non existante, ALORS je reçois une erreur\n")
    @Test
    void testUpdateTaskWithAInvalidExistingTaskName() {
        TaskDetailDTO taskDetailDTO = TaskDetailDTO.builder()
                .title("Test")
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .usernameAssignee("demo")
                .build();

        assertThrows(TaskNotFound.class, () -> taskController.updateTask("Tache 12", taskDetailDTO));
    }

    @DisplayName("Lorsque je tente de mettre à jour une tâche avec un nom trop long, ALORS je reçois une erreur\n")
    @Test
    void testUpdateTaskWithAInvalidTaskName() {
        TaskDetailDTO taskDetailDTO = TaskDetailDTO.builder()
                .title("e".repeat(51))
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .usernameAssignee("demo")
                .build();

        assertThrows(TaskNameTooLong.class, () -> taskController.updateTask("Tache 12", taskDetailDTO));
    }

    @DisplayName("Lorsque je tente de mettre à jour une tâche avec une description trop longue, ALORS je reçois une erreur\n")
    @Test
    void testUpdateTaskWithAInvalidTaskDescription() {
        TaskDetailDTO taskDetailDTO = TaskDetailDTO.builder()
                .title("Test")
                .description("e".repeat(501))
                .dueDate("2023-01-01")
                .storyPoints(1)
                .usernameAssignee("demo")
                .build();

        assertThrows(TaskDescriptionTooLong.class, () -> taskController.updateTask("Tache 12", taskDetailDTO));
    }

    @DisplayName("Lorsque je tente de mettre à jour une tâche avec un username trop long, ALORS je reçois une erreur\n")
    @Test
    void testUpdateTaskWithAInvalidUsername() {
        TaskDetailDTO taskDetailDTO = TaskDetailDTO.builder()
                .title("")
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .usernameAssignee("e".repeat(51))
                .build();

        assertThrows(TaskPayloadInvalid.class, () -> taskController.updateTask("Tache 12", taskDetailDTO));
    }

    @DisplayName("Lorsque je tente de mettre à jour une tâche avec des données valides, ALORS je récupère la tâche mise à jour\n")
    @Test
    void testUpdateTaskWithAValidTask() {
        TaskDetailDTO taskDetailDTO = TaskDetailDTO.builder()
                .title("Test")
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(4)
                .usernameAssignee("demo")
                .build();

        ResponseEntity<TaskDetailDTO> responseEntity = taskController.updateTask("Tache 1", taskDetailDTO);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        TaskDetailDTO task = responseEntity.getBody();

        assertNotNull(task);
        assertEquals(4, task.storyPoints());
    }

}
