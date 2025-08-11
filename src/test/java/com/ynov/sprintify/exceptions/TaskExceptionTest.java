package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.task.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
class TaskExceptionTest {

    @DisplayName("Lorsque une TaskNameTooLong est lancée, alors le message est correct")
    @Test
    void testTaskNameTooLong() {
        TaskNameTooLong taskNameTooLong = new TaskNameTooLong();
        assertEquals("Task name too long", taskNameTooLong.getMessage());
    }

    @DisplayName("Lorsque une TaskDescriptionTooLong est lancée, alors le message est correct")
    @Test
    void testTaskDescriptionTooLong() {
        TaskDescriptionTooLong taskDescriptionTooLong = new TaskDescriptionTooLong();
        assertEquals("Task description too long", taskDescriptionTooLong.getMessage());
    }

    @DisplayName("Lorsque une TaskPayloadInvalid est lancée, alors le message est correct")
    @Test
    void testTaskPayloadInvalid() {
        TaskPayloadInvalid taskPayloadInvalid = new TaskPayloadInvalid();
        assertEquals("Task payload invalid", taskPayloadInvalid.getMessage());
    }

    @DisplayName("Lorsque une TaskNotFound est lancée, alors le message est correct")
    @Test
    void testTaskNotFound() {
        TaskNotFound taskNotFound = new TaskNotFound();
        assertEquals("Task not found", taskNotFound.getMessage());
    }

    @DisplayName("Lorsque une TaskDueDateTooLong est lancée, alors le message est correct")
    @Test
    void testTaskDueDateTooLong() {
        TaskDueDateTooLong taskDueDateTooLong = new TaskDueDateTooLong();
        assertEquals("Task due date too long", taskDueDateTooLong.getMessage());
    }


}
