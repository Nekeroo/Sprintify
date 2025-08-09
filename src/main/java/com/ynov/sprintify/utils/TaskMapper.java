package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.task.TaskDTO;
import com.ynov.sprintify.models.Task;

public class TaskMapper {

    public static TaskDTO taskToTaskDTO(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .dueDate(task.getDueDate().toString())
                .usernameAssignee(task.getAssignee().getUsername())
                .storyPoints(task.getStoryPoints())
                .build();
    }

}
