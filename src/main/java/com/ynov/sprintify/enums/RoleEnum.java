package com.ynov.sprintify.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    USER(1, "User"),
    ADMIN(2, "Admin");

    private final int id;
    private final String label;

    RoleEnum(int id, String label) {
        this.id = id;
        this.label = label;
    }
}
