package com.ynov.sprintify.dto.project;


import lombok.*;

@Builder
public record ProjectOverviewDTO(String name, String usernameOwner, int nbSprint) { }
