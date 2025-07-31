package com.ynov.sprintify.services;

import com.ynov.sprintify.models.Sprint;
import com.ynov.sprintify.models.Task;
import com.ynov.sprintify.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintService sprintService;

    public TaskService(TaskRepository taskRepository, SprintService sprintService) {
        this.taskRepository = taskRepository;
        this.sprintService = sprintService;
    }

    public List<Task> getAllTaskBySprint(String name) {
        Sprint sprint = sprintService.findSprintByName(name);

        return taskRepository.findAllBySprintId(sprint.getId());
    }

}
