package com.backand.tracker.modules.project.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectReqDto {

    @NotNull
    private String name;

    @NotNull
    private String descriptions;
}
