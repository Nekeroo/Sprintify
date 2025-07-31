package com.ynov.sprintify.controllers;

import com.ynov.sprintify.services.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    public TaskController(TaskService taskService) {
    }



}
