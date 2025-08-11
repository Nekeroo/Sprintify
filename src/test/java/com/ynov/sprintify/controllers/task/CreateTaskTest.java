package com.ynov.sprintify.controllers.task;

import com.ynov.sprintify.controllers.TaskController;
import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.exceptions.task.TaskDescriptionTooLong;
import com.ynov.sprintify.exceptions.task.TaskDueDateTooLong;
import com.ynov.sprintify.exceptions.task.TaskNameTooLong;
import com.ynov.sprintify.payloads.TaskCreationPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class CreateTaskTest {

    @Autowired
    private TaskController taskController;

    @DisplayName("Lorsque je saisis un payload valide, alors je reçois les détails de ma tâche créee")
    @Test
    void testCreateTaskWithAValidPayload() {
        TaskCreationPayload taskCreationPayload = TaskCreationPayload.builder()
                .name("Test")
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .assignee("demo")
                .build();

        ResponseEntity<TaskDetailDTO> response = taskController.createTask("Sprint 1", taskCreationPayload);

        TaskDetailDTO taskDetailDTO = response.getBody();

        assertNotNull(taskDetailDTO);
        assertEquals(taskCreationPayload.name(), taskDetailDTO.title());
        assertEquals("Ready", taskDetailDTO.status());
    }

    @DisplayName("Lorsque je tente de créer une tâche sur un sprint non existant, alors je reçois une erreur")
    @Test
    void testCreateTaskWithAInvalidSprintName() {
        TaskCreationPayload taskCreationPayload = TaskCreationPayload.builder()
                .name("Test")
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .assignee("demo")
                .build();

        assertThrows(SprintNotFound.class, () -> taskController.createTask("Sprint 2", taskCreationPayload));
    }

    @DisplayName("Lorsque je tente de créer une tâche avec un nom trop long, alors je reçois une erreur")
    @Test
    void testCreateTaskWithAInvalidTaskName() {
        TaskCreationPayload taskCreationPayload = TaskCreationPayload.builder()
                .name("e".repeat(51))
                .description("Test")
                .dueDate("2023-01-01")
                .storyPoints(1)
                .assignee("demo")
                .build();

        assertThrows(TaskNameTooLong.class, () -> taskController.createTask("Sprint 1", taskCreationPayload));
    }

    @DisplayName("Lorsque je tente de créer une tâche avec une description trop longue, alors je reçois une erreur")
    @Test
    void testCreateTaskWithAInvalidTaskDescription() {
        TaskCreationPayload taskCreationPayload = TaskCreationPayload.builder()
                .name("Test")
                .description("e".repeat(501))
                .dueDate("2023-01-01")
                .storyPoints(1)
                .assignee("demo")
                .build();

        assertThrows(TaskDescriptionTooLong.class, () -> taskController.createTask("Sprint 1", taskCreationPayload));
    }

    @DisplayName("Lorsque je tente de créer une tâche avec une date attendue trop longue, alors je reçois une erreur")
    @Test
    void testCreateTaskWithAInvalidTaskStartDate() {
        TaskCreationPayload taskCreationPayload = TaskCreationPayload.builder()
                .name("Test")
                .description("Test")
                .dueDate("zefzfe".repeat(51))
                .storyPoints(1)
                .assignee("demo")
                .build();

        assertThrows(TaskDueDateTooLong.class, () -> taskController.createTask("Sprint 1", taskCreationPayload));
    }
}
