package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequestDto {
    @NotNull(message = "Title cannot be empty")
    @Size(min = 4, max = 35, message = "Number of characters should be from 4 to 35")
    private String title;

    @NotNull(message = "Desription cannot be empty")
    @Size(min = 10, max = 200, message = "Number of characters should be from 10 to 200")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
