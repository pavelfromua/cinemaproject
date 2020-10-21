package com.dev.cinema.model.dto;

import com.dev.cinema.annotation.EmailConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDto {
    @EmailConstraint
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotNull(message = "Repeat password cannot be empty")
    @Size(min = 8, message = "Repeat password must be at least 8 characters")
    private String repeatPassword;

    public UserRequestDto() {

    }

    public UserRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
