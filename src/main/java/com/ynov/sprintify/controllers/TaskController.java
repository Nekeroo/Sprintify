package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.payloads.TaskCreationPayload;
import com.ynov.sprintify.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<TaskDetailDTO>> getTaskBySprintName(@PathVariable String name) {
        return ResponseEntity.ok(taskService.getAllTaskBySprint(name));
    }

    @PostMapping("/{name}")
    public ResponseEntity<TaskDetailDTO> createTask(@PathVariable String name, @RequestBody TaskCreationPayload taskDetailDTO) {

        return ResponseEntity.ok(taskService.createTask(name, taskDetailDTO));
    }

}
