package org.example.rentproxy.service.user;

import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);
    UserDto findUserByName(String username);
    <T> T getUserParam(long userId, UserParamName userParamName, Class<T> requiredType);
}
