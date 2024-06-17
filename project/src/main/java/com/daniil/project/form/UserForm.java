package com.daniil.project.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

}
