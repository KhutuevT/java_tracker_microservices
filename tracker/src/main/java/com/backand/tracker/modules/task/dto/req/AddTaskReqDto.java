package com.backand.tracker.modules.task.dto.req;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class AddTaskReqDto {

    @NotNull
    String name;

    @NotNull
    String descriptions;
}
