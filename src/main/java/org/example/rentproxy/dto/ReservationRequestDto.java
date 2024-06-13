package org.example.rentproxy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
public class ReservationRequestDto {
    private Long id;
    private UserDto userDto;
    @Valid
    @NotNull
    private PostDto postDto;
    private Boolean confirmed;
    private Boolean archived;
    private LocalDate date;
}
