package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.SprintDTO;
import com.ynov.sprintify.models.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    private SprintMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static SprintDTO sprintToSprintDTO(Sprint sprint) {
        return SprintDTO.builder()
                .name(sprint.getName())
                .startDate(sprint.getStartDate().toString())
                .endDate(sprint.getEndDate().toString())
                .build();
    }

}
