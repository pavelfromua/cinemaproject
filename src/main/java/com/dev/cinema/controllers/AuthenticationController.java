package com.dev.cinema.controllers;

import com.dev.cinema.exceptions.RegistrationException;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.model.dto.mapper.UserMapper;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/registration")
    public UserResponseDto registration(@RequestBody UserRequestDto requestDto) {
        try {
            return userMapper.toDto(authenticationService
                    .register(requestDto.getEmail(), requestDto.getPassword()));
        } catch (RegistrationException e) {
            e.printStackTrace();
        }

        return new UserResponseDto();
    }
}
