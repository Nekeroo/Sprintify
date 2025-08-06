package com.ynov.sprintify.payloads;

import com.ynov.sprintify.dto.UserDTO;
import lombok.Builder;

@Builder
public record ProjectCreationPayload (
    String name,
    String description,
    UserDTO owner
) {
}
