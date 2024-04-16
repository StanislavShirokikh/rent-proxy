package org.example.rentproxy.controller;

import org.example.rentproxy.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserController {
    @PostMapping("/register")
    UserDto register(@RequestBody UserDto userDto);
}
