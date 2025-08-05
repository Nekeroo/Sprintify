package com.ynov.sprintify.utils;

import com.ynov.sprintify.dto.UserDTO;
import com.ynov.sprintify.models.User;

public class UserMapper {

    public static UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roleName(user.getRole().getName())
                .build();
    }
}
