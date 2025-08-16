package com.ynov.sprintify.controllers;

import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.payloads.TaskCreationPayload;
import com.ynov.sprintify.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        return ResponseEntity.ok(taskService.getAllTaskBySprint(decodedName));
    }

    @PostMapping("/{name}")
    public ResponseEntity<TaskDetailDTO> createTask(@PathVariable String name, @RequestBody TaskCreationPayload taskDetailDTO) {

        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        return ResponseEntity.ok(taskService.createTask(decodedName, taskDetailDTO));
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<TaskDetailDTO> updateTask(@PathVariable String name,@RequestBody TaskDetailDTO taskCreationPayload) {

        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        return ResponseEntity.ok(taskService.updateTask(decodedName, taskCreationPayload));
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteTask(@PathVariable String name) {
        String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8);

        taskService.deleteTask(decodedName);
        return ResponseEntity.ok().build();
    }


}
