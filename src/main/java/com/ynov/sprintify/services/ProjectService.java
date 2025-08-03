package com.ynov.sprintify.services;

import com.ynov.sprintify.exceptions.project.ProjectNotFound;
import com.ynov.sprintify.models.Project;
import com.ynov.sprintify.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectByName(String name) {
        return projectRepository.findByName(name).orElseThrow(ProjectNotFound::new);
    }

}
