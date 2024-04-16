package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public UserDto register(UserDto userDto) {
        return userService.createUser(userDto);
    }
}
