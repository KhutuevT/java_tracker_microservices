package com.backand.tracker.modules.auth.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthReqDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}


