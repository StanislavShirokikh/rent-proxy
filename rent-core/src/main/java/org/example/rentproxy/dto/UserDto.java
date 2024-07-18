package org.example.rentproxy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String firstName;
    @NotBlank
    @Size(max = 20)
    private String secondName;
    @NotBlank
    @Size(max = 20)
    private String lastName;
    @NotBlank
    @Size(min = 1, max = 20)
    private String login;
    @NotBlank
    @Size(min = 4, max = 30)
    @Pattern(regexp = "^.{4,}$")
    private String password;
}
