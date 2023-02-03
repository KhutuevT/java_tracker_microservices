package com.backand.tracker.modules.task.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTaskReqDto {
    @NotNull
    String name;

    @NotNull
    String descriptions;
}
