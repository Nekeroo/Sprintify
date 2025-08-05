package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.ProjectDTO;
import com.ynov.sprintify.dto.SprintDTO;
import com.ynov.sprintify.models.Project;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectMapper {

    private ProjectMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ProjectDTO projectToProjectDTO(Project project) {

        List<SprintDTO> sprintDTOs = project.getSprints() == null || project.getSprints().isEmpty()
                ? List.of()
                : project.getSprints().stream()
                .map(SprintMapper::sprintToSprintDTO)
                .toList();

        return ProjectDTO.builder()
                .name(project.getName())
                .sprints(sprintDTOs)
                .description(project.getDescription())
                .owner(UserMapper.userToUserDTO(project.getOwner()))
                .build();
    }

}
