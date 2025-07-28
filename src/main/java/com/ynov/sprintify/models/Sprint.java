package com.ynov.sprintify.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Sprint {

    private Long id;

    private String name;

    private List<Task> tasks;

}
