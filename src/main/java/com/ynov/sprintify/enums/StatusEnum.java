package com.ynov.sprintify.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    READY("Ready"),

    IN_PROGRESS("In Progress"),

    IN_TEST("In Test"),

    DONE("Done"),

    UNKNWOW("Unknown");


    private final String label;

    StatusEnum(String label) {
        this.label = label;
    }

    public static StatusEnum fromString(String status) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.name().equalsIgnoreCase(status)) {
                return statusEnum;
            }
        }
        return UNKNWOW;
    }

}
