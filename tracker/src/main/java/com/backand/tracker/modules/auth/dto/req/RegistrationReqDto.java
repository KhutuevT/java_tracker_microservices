package com.backand.tracker.modules.auth.dto.req;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Data
public class RegistrationReqDto {

    @NotNull
    private String username;

    @Email
    private String email;

    @NotNull
    private String password;
}
