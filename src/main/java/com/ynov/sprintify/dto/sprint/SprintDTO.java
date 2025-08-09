package com.ynov.sprintify.dto.sprint;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SprintDTO {

    private String name;
    private String description;
    private String startDate;
    private String endDate;

}
