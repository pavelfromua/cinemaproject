package com.dev.cinema.model.dto.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());

        return dto;
    }
}
