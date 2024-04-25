package org.example.rentproxy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequestDto {
    private Long id;
    private UserDto userDto;
    private PostDto postDto;
    private LocalDate date;
}