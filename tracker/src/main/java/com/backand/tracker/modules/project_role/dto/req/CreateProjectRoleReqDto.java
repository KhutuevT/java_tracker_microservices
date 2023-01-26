package com.backand.tracker.modules.project_role.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateProjectRoleReqDto {

    @NotNull
    private String name;
}
