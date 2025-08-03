package com.ynov.sprintify.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record RegisterRequest(String username, String password, String email) {

}
