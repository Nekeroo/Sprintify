package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.project.ProjectDetailsDTO;
import com.ynov.sprintify.dto.project.ProjectOverviewDTO;
import com.ynov.sprintify.models.Project;
import com.ynov.sprintify.payloads.ProjectCreationPayload;
import com.ynov.sprintify.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectOverviewDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProjectDetailsDTO> getProjectByName(@PathVariable String name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);


        return ResponseEntity.ok(projectService.getProjectByNameToDTO(decodedName));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProject(@PathVariable String name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        Project project = projectService.getProjectByName(decodedName);

        projectService.deleteProject(project);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<ProjectOverviewDTO> createProject(@RequestBody ProjectCreationPayload projectPayload) {
        return ResponseEntity.ok(projectService.createProject(projectPayload));
    }


}
