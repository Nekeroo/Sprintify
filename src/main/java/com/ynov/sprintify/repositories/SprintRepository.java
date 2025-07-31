package com.ynov.sprintify.repositories;

import com.ynov.sprintify.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Optional<Sprint> findByName(String name);

}