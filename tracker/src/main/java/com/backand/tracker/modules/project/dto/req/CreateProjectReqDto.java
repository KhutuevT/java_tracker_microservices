package com.backand.tracker.modules.project.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateProjectReqDto {

    @NotNull
    private String name;

    @NotNull
    private String descriptions;
}
