package com.ynov.sprintify.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    READY(1, "Ready"),

    IN_PROGRESS(2, "In Progress"),

    IN_TEST(3, "In Test"),

    DONE(4, "Done");


    private final int id;
    private final String label;

    StatusEnum(int id, String label) {
        this.id = id;
        this.label = label;
    }
}
