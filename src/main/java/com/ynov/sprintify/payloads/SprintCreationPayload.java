package com.ynov.sprintify.payloads;

import lombok.Builder;

@Builder
public record SprintCreationPayload (

        String name,
        String description,
        String startDate,
        String endDate

){
}
