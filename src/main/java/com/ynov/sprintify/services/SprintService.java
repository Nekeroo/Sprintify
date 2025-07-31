package com.ynov.sprintify.services;

import com.ynov.sprintify.exceptions.sprint.SprintNotFound;
import com.ynov.sprintify.models.Sprint;
import com.ynov.sprintify.repositories.SprintRepository;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint findSprintByName(String name) {
        return sprintRepository.findByName(name).orElseThrow(SprintNotFound::new);
    }
}
