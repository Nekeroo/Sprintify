package com.ynov.sprintify.repositories;

import com.ynov.sprintify.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllBySprintId(Long sprintId);

}
