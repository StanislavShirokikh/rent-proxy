package org.example.rentproxy.config;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.User;
import org.example.rentproxy.service.mapper.DtoMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MappingTest {
    @Autowired
    private DtoMapper modelMapper;
    @Test
    public void mapToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Иван");
        user.setSecondName("Иванович");
        user.setLastName("Иванов");
        user.setLogin("mr_ivanov");
        user.setPassword("1234");

        UserDto userDto = modelMapper.map(user, UserDto.class);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getSecondName(), userDto.getSecondName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getLogin(), userDto.getLogin());
    }

    @Test
    public void mapToPostDto() {
        Post post = new Post();
        User user = new User();
        user.setId(2L);
        user.setFirstName("Анна");
        user.setSecondName("Ивановна");
        user.setLastName("Иванова");
        user.setLogin("ms_ivanov");
        user.setPassword("12345678");
        post.setUser(user);
        PostDto postDto = modelMapper.map(post, PostDto.class);

        System.out.println(postDto);
    }
}