package com.ynov.sprintify.payloads;

import lombok.Builder;

@Builder
public record TaskCreationPayload (String name, String description, String dueDate, int storyPoints, String assignee) { }