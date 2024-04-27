package org.example.rentproxy.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
}
