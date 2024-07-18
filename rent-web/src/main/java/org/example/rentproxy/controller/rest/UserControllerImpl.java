package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.mapper.Mapper;
import org.example.rentproxy.response.UserResponse;
import org.springframework.web.bind.annotation.RestController;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.service.user.UserService;

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
