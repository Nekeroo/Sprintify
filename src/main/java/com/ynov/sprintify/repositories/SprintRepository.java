package com.ynov.sprintify.repositories;

import com.ynov.sprintify.models.Sprint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SprintRepository extends CrudRepository<Sprint, Long> {

    Optional<Sprint> findByName(String name);

    void deleteByName(String name);

    List<Sprint> findALlByProjectId(Long projectId);
}