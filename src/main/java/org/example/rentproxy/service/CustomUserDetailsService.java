package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.RoleRepository;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.User;
import org.example.rentproxy.mapper.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Mapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

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
}
