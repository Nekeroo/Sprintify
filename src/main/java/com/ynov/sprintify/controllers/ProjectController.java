package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.ProjectDTO;
import com.ynov.sprintify.models.Project;
import com.ynov.sprintify.payloads.ProjectCreationPayload;
import com.ynov.sprintify.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProjectDTO> getProjectByName(@PathVariable String name) {
        return ResponseEntity.ok(projectService.getProjectByNameToDTO(name));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProject(@PathVariable String name) {

        Project project = projectService.getProjectByName(name);

        projectService.deleteProject(project);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectCreationPayload projectPayload) {
        return ResponseEntity.ok(projectService.createProject(projectPayload));
    }

}
