package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.models.Task;

public class TaskMapper {

    public static TaskDetailDTO taskToTaskDTO(Task task) {
        return TaskDetailDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().getLabel())
                .dueDate(task.getDueDate().toString())
                .usernameAssignee(task.getAssignee().getUsername())
                .storyPoints(task.getStoryPoints())
                .build();
    }

}
