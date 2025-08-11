package com.ynov.sprintify.payloads;

import lombok.Builder;

@Builder
public record LoginRequest(String username, String password) {

}
