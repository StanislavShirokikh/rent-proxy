package org.example.rentproxy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.mapper.Mapper;
import org.example.rentproxy.repository.jpa.RoleRepository;
import org.example.rentproxy.repository.jpa.UserParameterRepository;
import org.example.rentproxy.repository.jpa.UserRepository;
import org.example.rentproxy.repository.jpa.entities.User;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserService {
    private final UserRepository userRepository;
    private final UserParameterRepository userParameterRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByLogin(userDto.getLogin()) != null) {
            log.warn("Найден пользователь с логином: {} Регистрация невозможна", userDto.getLogin());
            return null;
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setSecondName(userDto.getSecondName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setRoles(Set.of(roleRepository.findByName("USER")));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUserById(long id) {
        return mapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public UserDto findUserByName(String username) {
        return mapper.map(userRepository.findByLogin(username), UserDto.class);
    }

    @Override
    public <T> T getUserParam(long userId, UserParamName userParamName, Class<T> requiredType) {
        UserParameter userParameter = userParameterRepository.findByNameAndUserId(userParamName.getName(), userId);
        return mapper.map(userParameter.getParamValue(), requiredType);
    }
}
