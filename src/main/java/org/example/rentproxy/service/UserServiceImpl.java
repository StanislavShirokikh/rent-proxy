package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDto userDto) {
        if (userRepository.findByLogin(userDto.getLogin()) == null) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setSecondName(userDto.getSecondName());
            user.setLastName(user.getLastName());
            user.setLogin(userDto.getLogin());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);
        }
    }
}
