package com.ynov.sprintify.dto.sprint;

import lombok.Builder;

@Builder
public record SprintOverviewDTO(String name, String startDate, String endDate, String description) {



}
