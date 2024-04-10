package org.example.rentproxy.controller;

import org.example.rentproxy.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    @PostMapping("account/registration")
    void register(@RequestBody UserDto userDto);
}
