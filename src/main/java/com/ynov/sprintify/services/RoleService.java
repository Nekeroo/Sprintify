package com.ynov.sprintify.services;

import com.ynov.sprintify.exceptions.roles.RoleNotFound;
import com.ynov.sprintify.models.Role;
import com.ynov.sprintify.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(RoleNotFound::new);
    }


}
