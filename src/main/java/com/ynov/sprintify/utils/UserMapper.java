package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.UserDTO;
import com.ynov.sprintify.models.Role;
import com.ynov.sprintify.models.User;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }

        Role role = user.getRole();
        if (role == null) {
            return null;
        }

        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roleName(role.getName())
                .build();
    }

    public static User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .role(Role.builder()
                        .name(userDTO.getRoleName())
                        .build())
                .build();
    }
}
