package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.response.UserResponse;
import org.example.rentproxy.service.UserService;
import org.example.rentproxy.mapper.Mapper;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final Mapper mapper;

    @Override
    public UserResponse register(UserDto userDto) {
        return mapper.map(userService.createUser(userDto), UserResponse.class);
    }
}
