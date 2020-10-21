package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull(message = "User Id cannot be empty")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
