package com.backand.tracker.modules.project.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectDto {

    @NotNull
    private String name;

    @NotNull
    private String descriptions;
}
