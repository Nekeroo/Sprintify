package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.exceptions.task.TaskDescriptionTooLong;
import com.ynov.sprintify.exceptions.task.TaskDueDateTooLong;
import com.ynov.sprintify.exceptions.task.TaskNameTooLong;
import com.ynov.sprintify.exceptions.task.TaskPayloadInvalid;
import com.ynov.sprintify.payloads.TaskCreationPayload;

public class TaskValidator {

    public TaskValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateTask(TaskCreationPayload taskCreationPayload) {
        if (taskCreationPayload.name().isEmpty() || taskCreationPayload.description().isEmpty() || taskCreationPayload.dueDate().isEmpty() || taskCreationPayload.storyPoints() < 0) {
            throw new TaskPayloadInvalid();
        }

        if (taskCreationPayload.name().length() > 50) {
            throw new TaskNameTooLong();
        }

        if (taskCreationPayload.description().length() > 500) {
            throw new TaskDescriptionTooLong();
        }

        if (taskCreationPayload.dueDate().length() > 50) {
            throw new TaskDueDateTooLong();
        }

    }

    public static void validateTaskUpdate(TaskDetailDTO taskCreationPayload) {
        if (taskCreationPayload.title().isEmpty() || taskCreationPayload.description().isEmpty() || taskCreationPayload.dueDate().isEmpty() || taskCreationPayload.storyPoints() < 0) {
            throw new TaskPayloadInvalid();
        }

        if (taskCreationPayload.title().length() > 50) {
            throw new TaskNameTooLong();
        }

        if (taskCreationPayload.description().length() > 500) {
            throw new TaskDescriptionTooLong();
        }

        if (taskCreationPayload.dueDate().length() > 50) {
            throw new TaskDueDateTooLong();
        }
    }

}
