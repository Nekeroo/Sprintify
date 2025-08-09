package com.ynov.sprintify.services;

import com.ynov.sprintify.dto.project.ProjectDetailsDTO;
import com.ynov.sprintify.dto.project.ProjectOverviewDTO;
import com.ynov.sprintify.exceptions.project.ProjectAlreadyExists;
import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.exceptions.users.UserNotFound;
import com.ynov.sprintify.models.Project;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.payloads.ProjectCreationPayload;
import com.ynov.sprintify.repositories.ProjectRepository;
import com.ynov.sprintify.repositories.UserRepository;
import com.ynov.sprintify.utils.ProjectMapper;
import com.ynov.sprintify.utils.ProjectValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectOverviewDTO> getAllProjects() {
        Iterable<Project> projectsFound = projectRepository.findAllWithSprints();

        List<Project> projects = new ArrayList<>();
        projectsFound.forEach(projects::add);

        return projects.stream().map(ProjectMapper::projectToProjectOverviewDTO).toList();
    }

    public ProjectDetailsDTO getProjectByNameToDTO(String name) {
        Project project = projectRepository.findByName(name).orElseThrow(ProjectNotFound::new);

        return ProjectMapper.projectToProjecDetailsDTO(project);
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name).orElseThrow(ProjectNotFound::new);
    }
    @Transactional
    public void deleteProject(Project project) {
        projectRepository.deleteByName(project.getName());
    }

    @Transactional
    public ProjectOverviewDTO createProject(ProjectCreationPayload projectPayload) {

        ProjectValidator.validateProject(projectPayload);

        projectRepository.findByName(projectPayload.name()).ifPresent(project -> {
            throw new ProjectAlreadyExists();
        });

        User user = userRepository.findByUsername(projectPayload.owner().getUsername()).orElseThrow(UserNotFound::new);

        Project project = Project.builder()
                .name(projectPayload.name())
                .description(projectPayload.description())
                .owner(user)
                .build();

        projectRepository.save(project);

        return ProjectMapper.projectToProjectOverviewDTO((project));
    }

}
