package com.ynov.sprintify.payloads;

import lombok.Builder;

@Builder
public record ProjectCreationPayload (
    String name,
    String description,
    String owner
) {
}
