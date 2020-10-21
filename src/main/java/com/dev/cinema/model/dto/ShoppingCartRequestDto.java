package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class ShoppingCartRequestDto {
    @NotNull(message = "MovieSession Id cannot be empty")
    private Long movieSessionId;

    @NotNull(message = "User Id cannot be empty")
    private Long userId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
