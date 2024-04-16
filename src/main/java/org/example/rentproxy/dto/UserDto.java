package org.example.rentproxy.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
    private String password;
}
