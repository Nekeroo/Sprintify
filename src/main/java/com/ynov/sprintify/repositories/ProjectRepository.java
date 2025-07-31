package com.ynov.sprintify.repositories;


import com.ynov.sprintify.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
