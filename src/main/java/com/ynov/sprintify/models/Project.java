package com.ynov.sprintify.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project {

    private Long id;

    private String name;

    private String description;

    private User owner;

    private List<Sprint> sprints;
}
