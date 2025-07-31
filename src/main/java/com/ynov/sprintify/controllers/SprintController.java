package com.ynov.sprintify.controllers;

import com.ynov.sprintify.models.Sprint;
import com.ynov.sprintify.models.Task;
import com.ynov.sprintify.services.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/{name}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String name) {
        Sprint sprint = sprintService.findSprintByName(name);

        return ResponseEntity.ok(sprint.getTasks());
    }

}
