package com.ynov.sprintify.dto.sprint;

import lombok.Builder;

@Builder
public record StatDTO (String name, int nbTask, long nbTaskDone, long nbTaskNotDone, int capacity) {

}
