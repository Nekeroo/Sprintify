package com.ynov.sprintify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String roleName;

}
