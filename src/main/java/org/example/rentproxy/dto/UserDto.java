package org.example.rentproxy.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String login;
}
