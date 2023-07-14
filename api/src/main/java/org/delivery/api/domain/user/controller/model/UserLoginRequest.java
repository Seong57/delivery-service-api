package org.delivery.api.domain.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
