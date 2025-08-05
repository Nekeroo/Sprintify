package com.ynov.sprintify.repositories;


import com.ynov.sprintify.models.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.sprints WHERE p.name = :name")
    Optional<Project> findByName(String name);

    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.sprints")
    List<Project> findAllWithSprints();

    void deleteByName(String name);
}
