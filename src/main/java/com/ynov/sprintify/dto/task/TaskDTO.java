package com.ynov.sprintify.dto.task;

import lombok.Builder;

@Builder
public record TaskDTO(String title, String description, String status, String dueDate, String usernameAssignee, int storyPoints) { }
