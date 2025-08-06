package com.ynov.sprintify.services;

import com.ynov.sprintify.dto.SprintDTO;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.exceptions.sprint.SprintAlreadyExists;
import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.models.Project;
import com.ynov.sprintify.models.Sprint;
import com.ynov.sprintify.payloads.SprintCreationPayload;
import com.ynov.sprintify.repositories.ProjectRepository;
import com.ynov.sprintify.repositories.SprintRepository;
import com.ynov.sprintify.utils.SprintMapper;
import com.ynov.sprintify.utils.SprintValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;

    public SprintService(SprintRepository sprintRepository, ProjectRepository projectRepository) {
        this.sprintRepository = sprintRepository;
        this.projectRepository = projectRepository;
    }

    public Sprint findSprintByName(String name) {
        return sprintRepository.findByName(name).orElseThrow(SprintNotFound::new);
    }

    @Transactional
    public void deleteSprint(String name) {
        Sprint sprint = sprintRepository.findByName(name).orElseThrow(SprintNotFound::new);

        sprintRepository.deleteByName(sprint.getName());
    }

    public SprintDTO createSprintToAProject(SprintCreationPayload sprintPayload, String projectName) {

        SprintValidator.validateSprint(sprintPayload);
        Optional<Sprint> existingSprint = sprintRepository.findByName(sprintPayload.name());
        if (existingSprint.isPresent()) throw new SprintAlreadyExists();
        Project project = projectRepository.findByName(projectName).orElseThrow(ProjectNotFound::new);

        Sprint sprint = Sprint.builder()
                .name(sprintPayload.name())
                .description(sprintPayload.description())
                .startDate(LocalDate.parse(sprintPayload.startDate()))
                .endDate(LocalDate.parse(sprintPayload.endDate()))
                .tasks(new ArrayList<>())
                .project(project)
                .build();

        sprintRepository.save(sprint);

        return SprintMapper.sprintToSprintDTO(sprint);
    }

}
