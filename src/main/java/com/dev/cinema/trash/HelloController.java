package com.dev.cinema.trash;

import com.dev.cinema.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController ////@Controller + @ResponseBody
public class HelloController {
    //@ResponseBody
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello123";
    }

    //@ResponseBody
    @GetMapping("/userDto")
    public UserResponseDto getUser() {
        UserResponseDto dto = new UserResponseDto();
        dto.setName("Bob");
        dto.setEmail("bob@i.ua");

        return dto;
    }
}
