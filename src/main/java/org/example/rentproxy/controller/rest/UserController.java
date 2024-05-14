package org.example.rentproxy.controller.rest;

import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.response.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserController {
    @PostMapping("/register")
    UserResponse register(@RequestBody UserDto userDto);
}
