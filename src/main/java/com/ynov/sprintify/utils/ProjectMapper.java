package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.project.ProjectDetailsDTO;
import com.ynov.sprintify.dto.project.ProjectOverviewDTO;
import com.ynov.sprintify.dto.sprint.SprintOverviewDTO;
import com.ynov.sprintify.models.Project;

import java.util.List;

public class ProjectMapper {

    private ProjectMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProjectDetailsDTO projectToProjecDetailsDTO(Project project) {

        List<SprintOverviewDTO> sprintDTOs = project.getSprints() == null || project.getSprints().isEmpty()
                ? List.of()
                : project.getSprints().stream()
                .map(SprintMapper::sprintToSprintOverbiewDTO)
                .toList();

        return ProjectDetailsDTO.builder()
                .name(project.getName())
                .sprints(sprintDTOs)
                .description(project.getDescription())
                .owner(UserMapper.userToUserDTO(project.getOwner()))
                .build();
    }


    public static ProjectOverviewDTO projectToProjectOverviewDTO(Project project) {

        int count = 0;

        if (project.getSprints() != null && !project.getSprints().isEmpty()) {
            count = project.getSprints().size();
        }

        return ProjectOverviewDTO.builder()
                .name(project.getName())
                .nbSprint(count)
                .usernameOwner(project.getOwner().getUsername())
                .build();
    }

}
