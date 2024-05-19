package org.example.rentproxy.service.user;

import org.example.rentproxy.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);

    <T> T getUserParam(long userId, UserParamName userParamName, Class<T> requiredType);

    default void k() {
        String value = getUserParam(12L, UserParamName.DEFAULT_CURRENCY, String.class);
    }
}
