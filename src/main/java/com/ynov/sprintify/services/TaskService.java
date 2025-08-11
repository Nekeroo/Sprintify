package com.ynov.sprintify.services;

import com.ynov.sprintify.dto.task.TaskDetailDTO;
import com.ynov.sprintify.enums.StatusEnum;
import com.ynov.sprintify.exceptions.task.TaskNotFound;
import com.ynov.sprintify.models.Sprint;
import com.ynov.sprintify.models.Task;
import com.ynov.sprintify.models.User;
import com.ynov.sprintify.payloads.TaskCreationPayload;
import com.ynov.sprintify.repositories.TaskRepository;
import com.ynov.sprintify.utils.TaskMapper;
import com.ynov.sprintify.utils.TaskValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintService sprintService;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, SprintService sprintService, UserService userService) {
        this.taskRepository = taskRepository;
        this.sprintService = sprintService;
        this.userService = userService;
    }

    public List<TaskDetailDTO> getAllTaskBySprint(String name) {
        Sprint sprint = sprintService.findSprintByName(name);

        return taskRepository.findAllBySprintId(sprint.getId())
                .stream()
                .map(TaskMapper::taskToTaskDTO)
                .toList();
    }

    public TaskDetailDTO createTask(String name, TaskCreationPayload taskCreationPayload) {

        TaskValidator.validateTask(taskCreationPayload);
        Sprint sprint = sprintService.findSprintByName(name);

        User user = userService.getUserByUsername(taskCreationPayload.assignee());

        Task task = Task.builder()
                .title(taskCreationPayload.name())
                .description(taskCreationPayload.description())
                .status(StatusEnum.READY)
                .dueDate(LocalDate.parse(taskCreationPayload.dueDate()))
                .assignee(user)
                .sprint(sprint)
                .storyPoints(taskCreationPayload.storyPoints())
                .build();

        taskRepository.save(task);

        return TaskMapper.taskToTaskDTO(task);
    }

    public TaskDetailDTO updateTask(String taskName, TaskDetailDTO taskDetailDTO) {
        TaskValidator.validateTaskUpdate(taskDetailDTO);

        Task task = taskRepository.findTaskByTitle(taskName).orElseThrow(TaskNotFound::new);
        User user = userService.getUserByUsername(taskDetailDTO.usernameAssignee());

        task.setTitle(taskDetailDTO.title());
        task.setDescription(taskDetailDTO.description());
        task.setStatus(StatusEnum.fromString(taskDetailDTO.status()));
        task.setDueDate(LocalDate.parse(taskDetailDTO.dueDate()));
        task.setAssignee(user);
        task.setStoryPoints(taskDetailDTO.storyPoints());

        taskRepository.save(task);

        return TaskMapper.taskToTaskDTO(task);
    }

}
