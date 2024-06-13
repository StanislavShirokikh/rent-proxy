package org.example.rentproxy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@Data
@Validated
public class PostDto {
    private Long id;
    private UserDto userDto;
    @NotNull
    @Valid
    private RentConditionInfoDto rentConditionInfoDto;
    @NotNull
    @Valid
    private ApartmentInfoDto apartmentInfoDto;
    @NotNull
    @Valid
    private HouseInfoDto houseInfoDto;
    @NotNull
    @Valid
    private RentTypeDto rentTypeDto;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotNull
    private LocalDate date;
}
