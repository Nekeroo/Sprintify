package com.ynov.sprintify.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    USER(1, "User"),
    ADMIN(2, "Admin"),
    UNKNOWN(0, "Unknown");

    private final int id;
    private final String label;

    RoleEnum(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static RoleEnum fromString(String role) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.name().equalsIgnoreCase(role)) {
                return roleEnum;
            }
        }
        return UNKNOWN;
    }
}
