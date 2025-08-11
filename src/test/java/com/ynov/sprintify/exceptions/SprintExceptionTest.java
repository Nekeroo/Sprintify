package com.ynov.sprintify.exceptions;

import com.ynov.sprintify.exceptions.sprint.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class SprintExceptionTest {

    @DisplayName("Lorsqu'une SprintPayloadInvalid est lancée, alors le message est correct")
    @Test
    void testSprintPayloadInvalid() {
        SprintPayloadInvalid sprintPayloadInvalid = new SprintPayloadInvalid();
        assertEquals("Sprint payload is invalid", sprintPayloadInvalid.getMessage());
    }

    @DisplayName("Lorsqu'une SprintNotFound est lancée, alors le message est correct")
    @Test
    void testSprintNotFound() {
        SprintNotFound sprintNotFound = new SprintNotFound();
        assertEquals("Sprint not found", sprintNotFound.getMessage());
    }

    @DisplayName("Lorsqu'une SprintNameTooLong est lancée, alors le message est correct")
    @Test
    void testSprintNameTooLong() {
        SprintNameTooLong sprintNameTooLong = new SprintNameTooLong();
        assertEquals("Sprint name is too long", sprintNameTooLong.getMessage());
    }

    @DisplayName("Lorsqu'une SprintDescriptionTooLong est lancée, alors le message est correct")
    @Test
    void testSprintNotFound2() {
        SprintDescriptionTooLong sprintDescriptionTooLong = new SprintDescriptionTooLong();
        assertEquals("Sprint description is too long", sprintDescriptionTooLong.getMessage());
    }

    @DisplayName("Lorsqu'une SprintAlreadyExists est lancée, alors le message est correct")
    @Test
    void testSprintAlreadyExists() {
        SprintAlreadyExists sprintAlreadyExists = new SprintAlreadyExists();
        assertEquals("Sprint already exists", sprintAlreadyExists.getMessage());
    }

}
