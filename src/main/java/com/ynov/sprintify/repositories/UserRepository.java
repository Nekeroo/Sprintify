package com.ynov.sprintify.repositories;

import com.ynov.sprintify.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.role")
    List<User> findAllWithRole();
}
