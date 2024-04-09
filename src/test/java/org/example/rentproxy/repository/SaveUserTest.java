package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class SaveUserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void saveUser() {
        User user = new User();
        user.setFirstName("Иван");
        user.setSecondName("Николаевич");
        user.setLastName("Петров");
        user.setLogin("IvanPetrov");
        user.setPassword(passwordEncoder.encode("пароль"));

        User savedUser = userRepository.save(user);

        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getSecondName(), savedUser.getSecondName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getLogin(), savedUser.getLogin());
        assertFalse(!savedUser.getPassword().equals(user.getPassword()));
        assertFalse(savedUser.getPassword().isBlank());
    }
}
