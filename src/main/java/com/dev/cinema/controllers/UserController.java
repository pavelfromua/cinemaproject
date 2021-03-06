package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.model.dto.mapper.UserMapper;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
//user/inject
//user/{userId}
//user/
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @RequestMapping(value = "/inject", method = RequestMethod.GET)
    public void injectIntoDb() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("test1@gmail.com");
        user.setPassword("111");
        userService.add(user);

        user = new User();
        user.setName("Tom");
        user.setEmail("test2@gmail.com");
        user.setPassword("222");
        userService.add(user);

        user = new User();
        user.setName("Garry");
        user.setEmail("test3@gmail.com");
        user.setPassword("333");
        userService.add(user);

        user = new User();
        user.setName("Mary");
        user.setEmail("test4@gmail.com");
        user.setPassword("444");
        userService.add(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new UserResponseDto();
        }

        return userMapper.toDto(user);
    }

    @RequestMapping(value = "/users/by-email?{email}", method = RequestMethod.GET)
    public UserResponseDto getByEmail(@PathVariable String email) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (!userOptional.isPresent()) {
            return new UserResponseDto();
        }

        return userMapper.toDto(userOptional.get());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> list = userService.getAll().stream()
                .map(user -> userMapper.toDto(user)).collect(Collectors.toList());

        return list;
    }
}
