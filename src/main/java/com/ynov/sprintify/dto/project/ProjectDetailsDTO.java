package com.ynov.sprintify.dto.project;

import com.ynov.sprintify.dto.sprint.SprintDTO;
import com.ynov.sprintify.dto.UserDTO;
import com.ynov.sprintify.dto.sprint.SprintOverviewDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record ProjectDetailsDTO(String name, String description, UserDTO owner, List<SprintOverviewDTO> sprints) {
}
