package com.dev.cinema.mapper;

import com.dev.cinema.UserResponseDto;
import com.dev.cinema.model.User;
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
