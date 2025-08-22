package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.sprint.SprintOverviewDTO;
import com.ynov.sprintify.models.Sprint;

public class SprintMapper {

    private SprintMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static SprintOverviewDTO sprintToSprintOverbiewDTO(Sprint sprint) {
        return SprintOverviewDTO.builder()
                .name(sprint.getName())
                .startDate(sprint.getStartDate().toString())
                .endDate(sprint.getEndDate().toString())
                .description(sprint.getDescription())
                .build();
    }

}
