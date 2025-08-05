package com.ynov.sprintify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ProjectDTO {

    private String name;

    private String description;

    private UserDTO owner;

    private List<SprintDTO> sprints;
}
