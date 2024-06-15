package org.example.rentproxy.controller.rest;

import jakarta.validation.Valid;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.response.UserResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Validated
public interface UserController {
    @PostMapping("/register")
    UserResponse register(@Valid @RequestBody UserDto userDto);
}
