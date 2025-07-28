package com.ynov.sprintify.models;

import com.ynov.sprintify.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Task {

    private Long id;

    private String title;

    private String description;

    private StatusEnum status;

    private LocalDate dueDate;

    private User assignee;

    private Project project;

}
